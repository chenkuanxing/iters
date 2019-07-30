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

@Service
public class PersonalFileServiceImpl extends ServiceImpl<PersonalFileMapper, PersonalFile> implements PersonalFileService {

    @Value("${file.filepath}")
    private String filepath;

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
        URL save = Thread.currentThread().getContextClassLoader().getResource("");
        String str = save.toString();
        str = str.substring(5, str.length());
        str = str.replaceAll("%20", " ");
        int num = str.indexOf("iters");//wgbs 为项目名，应用到不同的项目中，这个需要修改！
        str = str.substring(0, num + "iters".length());
        FileUtils.fileUpload(file, filepath);
        String filePath = filepath;
        return str + filePath + file.getOriginalFilename();
    }
}
