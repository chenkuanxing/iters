package com.xinghui.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.dot.TrainDot;
import com.xinghui.entity.Train;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface TrainMapper extends BaseMapper<Train> {

    List<Train> trainPage(Page page, @Param("trainDot") TrainDot trainDot, @Param("userId") String userId);

    Map<String,Object> creatTrain (TrainDot trainDot);

    List<TrainDot> queryTrain(TrainDot trainDot, @Param("id") String id);
}
