package com.xinghui.controller;

import com.xinghui.ResultDto;
import com.xinghui.dot.JournalDot;
import com.xinghui.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalController extends BaseController {

    @Autowired
    private JournalService journalService;


    /**
     * 查询日志列表
     */
    @GetMapping(value = "/listPage")
    public ResultDto listPage(Integer offset, Integer limit, JournalDot journalDot) {
        return success(journalService.listPage(offset, limit, journalDot));
    }

    /**
     * 添加和更新
     *
     * @return
     */
    @PostMapping(value = {"/create", "/update"})
    public ResultDto create(JournalDot journalDot) {
        return success(journalService.create(journalDot));
    }


}