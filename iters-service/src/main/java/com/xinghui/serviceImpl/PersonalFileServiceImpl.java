package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.common.Constants;
import com.xinghui.dot.PersonalFileDot;
import com.xinghui.entity.PersonalFile;
import com.xinghui.mapper.PersonalFileMapper;
import com.xinghui.service.PersonalFileService;
import com.xinghui.utils.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
public class PersonalFileServiceImpl extends ServiceImpl<PersonalFileMapper, PersonalFile> implements PersonalFileService {

    @Value("${file.filepaths}")
    private String filepath;

    @Value("${file.uploadpath}")
    private String uploadpath;

    @Override
    public boolean create(PersonalFileDot personalFileDot) {
        PersonalFile personalFile = new PersonalFile();
        BeanUtils.copyProperties(personalFileDot, personalFile);
        List<PersonalFile> list = this.list(new QueryWrapper<PersonalFile>().lambda().eq(PersonalFile::getStatus, Constants.status.TRUE).orderByDesc(PersonalFile::getType));
        if (!CollectionUtils.isEmpty(list)) {
            personalFile.setType(list.get(0).getType() + 1);
        } else {
            personalFile.setType(1);
        }
        return this.save(personalFile);
    }

    @Override
    public String fileUpload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = filepath;
        String newFileName = UUID.randomUUID().toString() + suffixName;
        String path = filePath + newFileName;
        FileUtils.fileUpload(file, uploadpath,newFileName);
        return path;
    }
}
