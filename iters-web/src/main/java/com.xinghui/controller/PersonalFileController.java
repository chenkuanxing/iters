package com.xinghui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinghui.ResultDto;
import com.xinghui.common.Constants;
import com.xinghui.dot.PersonalFileDot;
import com.xinghui.entity.PersonalFile;
import com.xinghui.service.PersonalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/personalFile")
public class PersonalFileController extends BaseController {

    @Autowired
    private PersonalFileService personalFileService;

    /**
     * 文件列表
     */
    @GetMapping(value = "/list/{fileType}")
    public ResultDto list(@PathVariable("fileType") Integer fileType) {
        List<PersonalFile> list = personalFileService.list(new QueryWrapper<PersonalFile>().lambda().eq(PersonalFile::getStatus, Constants.status.TRUE).eq(PersonalFile::getFileType, fileType));
        URL save = Thread.currentThread().getContextClassLoader().getResource("");
        String str = save.toString();
        str = str.substring(5, str.length());
        str = str.replaceAll("%20", " ");
        int num = str.indexOf("iters");//wgbs 为项目名，应用到不同的项目中，这个需要修改！
        str = str.substring(0, num + "iters".length()) + "/iters-web/src/main/resources/";
        if (!CollectionUtils.isEmpty(list)) {
            for (PersonalFile personalFile : list) {
                personalFile.setUrl(personalFile.getUrl().replace(str, ""));
            }
        }
        return success(list);
    }

    /**
     * 添加
     *
     * @return
     */
    @PostMapping(value = "/create")
    public ResultDto create(PersonalFileDot personalFileDot) {
        return success(personalFileService.create(personalFileDot));
    }

    /**
     * 上传图片
     *
     * @return
     */
    @PostMapping(value = "/fileUpload")
    public ResultDto fileUpload(MultipartFile file) {
        return success(personalFileService.fileUpload(file));
    }
}