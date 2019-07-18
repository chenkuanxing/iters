package com.xinghui.controller;

import com.xinghui.ResultDto;
import com.xinghui.dot.PublishDot;
import com.xinghui.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class PublishController extends BaseController {

    @Autowired
    private PublishService publishService;

    /**
     * 查询公告列表
     *
     * @return
     */
    @GetMapping(value = "/listPage")
    public ResultDto listPage(Integer offset, Integer limit, PublishDot publishDot) {
        return success(publishService.listPage(offset, limit, publishDot));
    }

    @GetMapping(value = "/delete/{id}")
    private ResultDto delete(@PathVariable("id") String id) {
        return success(publishService.removeById(id));
    }
}