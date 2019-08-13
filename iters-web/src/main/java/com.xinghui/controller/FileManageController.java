package com.xinghui.controller;

import com.xinghui.ResultDto;
import com.xinghui.dot.FileManageDot;
import com.xinghui.service.FileManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/fileManage")
public class FileManageController extends BaseController {

    @Autowired
    private FileManageService fileManageService;

    /**
     * 文件列表
     */
    @GetMapping(value = "/listPage")
    public ResultDto listPage(Integer offset, Integer limit, FileManageDot fileManageDot) {
        return success(fileManageService.listPage(offset, limit, fileManageDot));
    }

    /**
     * 文件上传
     *
     * @return
     */
    @PostMapping(value = "/fileUpload/{type}/{fileType}")
    public ResultDto fileUpload(MultipartFile file, @PathVariable("type") Integer type, @PathVariable("fileType") Integer fileType) {
        return success(fileManageService.fileUpload(file, type, fileType));
    }

    /**
     * 文件下載
     *
     * @return
     */
    @GetMapping(value = "/download/{id}")
    public void download(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        fileManageService.download(id, response);
    }

    /**
     * 文件删除
     *
     * @return
     */
    @GetMapping(value = "/delete/{id}")
    public ResultDto delete(@PathVariable("id") String id) {
        return success(fileManageService.delete(id));
    }

}