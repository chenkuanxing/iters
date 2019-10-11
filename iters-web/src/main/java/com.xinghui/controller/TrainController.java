package com.xinghui.controller;
import com.xinghui.dot.TrainDot;
import com.xinghui.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xinghui.service.TrainService;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController extends BaseController {
    @Autowired(required = false)
    private TrainService trainService;
    /**
     * 查询培训管理列表
     */
    @GetMapping(value = "/trainPage")
    public ResultDto trainPage(Integer offset, Integer limit, TrainDot trainDot) {
        return success(trainService.trainPage(offset, limit, trainDot));
    }
    @PostMapping(value = "/creatTrain")
    public ResultDto create(TrainDot trainDot) {
        return success(trainService.creatTrain(trainDot));
    }
    /**
     * 删除培训管理内容
     * @return
     */
    @GetMapping(value = "/deleteTrain/{id}")
    private ResultDto deleteTrain(@PathVariable("id") String id) {
        return success(trainService.removeById(id));
    }
    /**
     * 培训管理查看详情
     * @return
     */
    @GetMapping(value = "/queryTrain/{id}")
    public ResultDto queryTrain(TrainDot trainDot,@PathVariable("id") String id) {
        List<TrainDot> trainListDots = trainService.queryTrain(trainDot,id);
        return success(trainListDots);
    }
}
