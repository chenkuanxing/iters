package com.xinghui.controller;
import com.xinghui.dot.RecodeDot;
import com.xinghui.ResultDto;
import com.xinghui.service.UserService;
import com.xinghui.utils.ExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.xinghui.service.RecodeServise;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/recode")
public class RecodeController extends BaseController {
    @Autowired
    private RecodeServise recodeServise;
    @Autowired
    private UserService userService;
    /**
     * 查询定制任务列表
     */
    @GetMapping(value = "/listRecodePage")
    public ResultDto listRecodePage(Integer offset, Integer limit, RecodeDot recodeDot) {
        return success(recodeServise.listRecodePage(offset, limit, recodeDot));
    }
    /**
     * 添加和更新定制任务
     * @return
     */
    @PostMapping(value = "/create")
    public ResultDto create(RecodeDot recodeDot) {
        return success(recodeServise.create(recodeDot));
    }
    /**
     * 删除定制任务
     * @return
     */
    @GetMapping(value = "/delete/{id}")
    private ResultDto delete(@PathVariable("id") String id) {
        return success(recodeServise.removeById(id));
    }
    /**
     * 导出定制任务
     * @return
     */
    @GetMapping(value = "/recodeExport")
    public void recodeExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<RecodeDot> recodeList = recodeServise.getRecodelist();
        ExcelUtils.exportExcel(recodeList, "定制任务统计表", "定制任务统计表", RecodeDot.class, "定制任务统计表.xls", response);
    }
    /**
     * 查询定制任务详情
     * @return
     */
    @GetMapping(value = "/query/{id}")
    public ResultDto query(@PathVariable("id") String id) {
        RecodeDot recodeDot = new RecodeDot();
        BeanUtils.copyProperties(recodeServise.getById(id), recodeDot);
        recodeDot.setPublishName(userService.getById(recodeDot.getPublisher()).getUserName());
        recodeDot.setPerformByName(userService.getById(recodeDot.getPerformBy()).getUserName());
        return success(recodeDot);
    }




}
