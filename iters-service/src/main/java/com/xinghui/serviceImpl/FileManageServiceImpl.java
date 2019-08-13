package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.FileManageDot;
import com.xinghui.entity.FileManage;
import com.xinghui.exception.CustException;
import com.xinghui.mapper.FileManageMapper;
import com.xinghui.service.FileManageService;
import com.xinghui.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

@Service
public class FileManageServiceImpl extends ServiceImpl<FileManageMapper, FileManage> implements FileManageService {

    @Value("${file.filepaths}")
    private String filepath;

    @Value("${file.uploadpath}")
    private String uploadpath;

    @Override
    public Page<FileManage> listPage(Integer offset, Integer limit, FileManageDot fileManageDot) {
        Page<FileManage> page = new Page<>(offset, limit);
        return page.setRecords(baseMapper.listPage(page, fileManageDot));
    }

    @Override
    public boolean fileUpload(MultipartFile file, Integer type, Integer fileType) {
        FileManage fileManage = new FileManage();
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        long size = file.getSize();
        fileManage.setName(fileName.replace(suffixName, ""));
        fileManage.setFormat(suffixName);
        fileManage.setType(type);
        fileManage.setFileType(fileType);
        fileManage.setSize(String.valueOf(size));
        // 设置文件存储路径

        String filePath = filepath;
        String newFileName = UUID.randomUUID().toString() + suffixName;
        String path = filePath + newFileName;
        fileManage.setUrl(path);
        FileUtils.fileUpload(file, uploadpath, newFileName);
        return this.save(fileManage);
    }

    @Override
    public void download(String id, HttpServletResponse resp) throws IOException {
        FileManage fileManage = this.getById(id);
        String fileName = null;
        try {
            fileName = new String((fileManage.getName() + fileManage.getFormat()).getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String replace = uploadpath.replace("\\:", ":").replace("\\\\", "\\").replace("&", "\\");

        String path = fileManage.getUrl().replace(filepath, "");

        File file = new File(replace+"/"+path);
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean delete(String id) {
        FileManage fileManage = this.getById(id);
        String url = fileManage.getUrl();
        String replaces = uploadpath.replace("\\:", ":").replace("\\\\", "\\").replace("&", "\\");
        String replace = url.replace(filepath, "");
        File file = new File(replaces+"/"+replace);
        file.delete();
        return this.removeById(id);
    }
}
