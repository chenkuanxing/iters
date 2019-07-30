package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.FileManageDot;
import com.xinghui.entity.FileManage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileManageService extends IService<FileManage> {

    Page<FileManage> listPage(Integer offset, Integer limit, FileManageDot fileManageDot);

    boolean fileUpload(MultipartFile file, Integer type, Integer fileType);

    void download(String id, HttpServletResponse response) throws IOException;

    boolean delete(String id);
}
