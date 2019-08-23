package com.xinghui.controller;

import com.xinghui.ResultDto;
import com.xinghui.dot.JournalDot;
import com.xinghui.dot.LocationStaticExportDot;
import com.xinghui.service.JournalService;
import com.xinghui.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/locationStatic")
public class LocationStaticController extends BaseController {

    @Autowired
    private JournalService journalService;

    @GetMapping("/staticCountNumberAll")
    public ResultDto departmentArticleSum(String beginTime, String endTime){
        return success(journalService.departmentArticleSum(beginTime,endTime));
    }
    @GetMapping("/staticTimes")
    public ResultDto departmentArticleSum(){
        return success(journalService.departmentArticleTimes());
    }
    @GetMapping("/exportStaticInformation")
    public void exportStaticInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<LocationStaticExportDot> exportStaticList = journalService.getStaticExportList();
        ExcelUtils.exportExcel(exportStaticList, "员工日志", "员工日志", LocationStaticExportDot.class, "员工日志.xls", response);
    }
}
