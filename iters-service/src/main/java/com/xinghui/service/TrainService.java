package com.xinghui.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.TrainDot;
import com.xinghui.entity.Train;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface TrainService extends IService<Train> {

    Page<Train> trainPage(Integer offset, Integer limit, TrainDot trainDot);

    Map<String,Object> creatTrain(TrainDot trainDot);

    List<TrainDot> queryTrain(TrainDot trainDot, @PathVariable("id") String id);
}
