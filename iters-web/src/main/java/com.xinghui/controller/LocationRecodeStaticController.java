package com.xinghui.controller;
import com.xinghui.ResultDto;
import com.xinghui.dot.LocationStaticExportDot;
import com.xinghui.service.RecodeService;
import com.xinghui.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/locationRecodeStatic")
public class LocationRecodeStaticController extends BaseController {

    @Autowired
    private RecodeService recodeService;
    /*
     *统计页面显示部分文章和数目
     */
    @GetMapping("/staticCountNumberAll")
    public ResultDto departmentArticleSum(String beginTime, String endTime){
        return success(recodeService.departmentArticleSum(beginTime,endTime));
    }
    /*
     *统计初始结束时间
     */
    @GetMapping("/staticTimes")
    public ResultDto departmentArticleSum(){
        return success(recodeService.departmentArticleTimes());
    }
    /*
     *统计文章导出的总结果
     */
    @GetMapping("/exportStaticInformation")
    public void exportStaticInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<LocationStaticExportDot> exportStaticList = recodeService.getStaticExportList();
        ExcelUtils.exportExcel(exportStaticList, "定制任务", "定制任务", LocationStaticExportDot.class, "定制任务.xls", response);
    }
}
