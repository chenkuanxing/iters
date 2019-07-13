package com.xinghui.controller;

import com.xinghui.ResultDto;
import com.xinghui.common.Constants;
import com.xinghui.dot.JournalDot;
import com.xinghui.entity.Journal;
import com.xinghui.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/delete/{id}")
    private ResultDto delete(@PathVariable("id")String id){
        Journal journal = journalService.getById(id);
        journal.setStatus(Constants.status.FALSE);
        return success(journalService.updateById(journal));
    }


}