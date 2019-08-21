package com.xinghui.controller;

import com.xinghui.ResultDto;
import com.xinghui.dot.LocationStaticDot;
import com.xinghui.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locationStatic")
public class LocationStaticController extends BaseController {

    @Autowired
    private JournalService journalService;

    @GetMapping("/staticCountNumberAll")
    public ResultDto departmentArticleSum(){
        return success(journalService.departmentArticleSum());
    }
}
