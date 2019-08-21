package com.xinghui.controller;
import com.xinghui.dot.LocationStaticDot;
import com.xinghui.ResultDto;
import com.xinghui.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locationStatic")
public class LocationStaticController extends BaseController {

    @Autowired
    private JournalService journalService;

    @GetMapping("/staticCountNumberAll")
    public ResultDto departmentArticleSum(){
      List <LocationStaticDot> locationStaticList = journalService.departmentArticleSum();
        System.out.print(locationStaticList);
        return success(locationStaticList);
    }
}
