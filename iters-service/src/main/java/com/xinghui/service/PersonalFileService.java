package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.PersonalFileDot;
import com.xinghui.entity.PersonalFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonalFileService extends IService<PersonalFile> {
    boolean create(PersonalFileDot personalFileDot);

    String fileUpload(MultipartFile file);

}
