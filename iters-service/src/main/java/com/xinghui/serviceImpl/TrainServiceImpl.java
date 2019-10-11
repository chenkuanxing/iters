package com.xinghui.serviceImpl;
import com.xinghui.dot.EmailDot;
import com.xinghui.dot.TrainDot;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.TrainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.entity.Train;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.xinghui.mapper.TrainMapper;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PathVariable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class TrainServiceImpl extends ServiceImpl<TrainMapper, Train> implements TrainService{
    @Override
    public Page<Train> trainPage(Integer offset, Integer limit, TrainDot trainDot) {
        Page<Train> page = new Page<>(offset, limit);
        if (!StringUtils.isEmpty(trainDot.getTrainTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                trainDot.setTrainTime(format.parse(trainDot.getTrainTimes()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isEmpty(trainDot.getEndTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                trainDot.setEndTime(format.parse(trainDot.getEndTimes()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return page.setRecords(baseMapper.trainPage(page, trainDot, SecurityUtils.getCurrentUserId()));
    }
    @Override
    public Map<String, Object> creatTrain(TrainDot trainDot) {
        Train train = new Train();
        BeanUtils.copyProperties(trainDot, train);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(trainDot.getTrainTime())) {
            //format()方法将Date转换成指定格式的String
            String trainTimes = format.format(trainDot.getTrainTime());
            trainDot.setTrainTimes(trainTimes);
        }
        if (!StringUtils.isEmpty(trainDot.getEndTime())) {
            //format()方法将Date转换成指定格式的String
            String endTimes = format.format(trainDot.getEndTime());
            trainDot.setEndTimes(endTimes);
        }
        Map<String, Object> trainsAddsLists = baseMapper.creatTrain(trainDot);

        return trainsAddsLists;
    }
    @Override
    public List<TrainDot> queryTrain(TrainDot trainDot, @PathVariable("id") String id) {
        Train train = new Train();
        BeanUtils.copyProperties(trainDot, train);
        List<TrainDot> trainQueryLists = baseMapper.queryTrain(trainDot, id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<TrainDot> trainQueryOnlyLists = null;
        int trainCount = 0;
        List<TrainDot> trainQueryAllLists = new ArrayList<>();
        for(TrainDot trainDots:trainQueryLists) {
            trainCount=trainCount+1;
            if (id.equals(trainDots.getId())) {
                if (!StringUtils.isEmpty(trainDots.getTrainTime())) {
                    //format()方法将Date转换成指定格式的String
                    String trainTimes = format.format(trainDots.getTrainTime());
                    trainDots.setTrainTimes(trainTimes);
                    trainQueryLists.add(trainDots);
                }
                if (!StringUtils.isEmpty(trainDots.getEndTime())) {
                    //format()方法将Date转换成指定格式的String
                    String endTimes = format.format(trainDots.getEndTime());
                    trainDots.setEndTimes(endTimes);
                    trainQueryLists.add(trainDots);
                }
                break;
            }
        }
        trainQueryOnlyLists = trainQueryLists;
        TrainDot trainDotsLists = trainQueryOnlyLists.get(trainCount - 1);
        trainQueryAllLists.add(trainDotsLists);
        train.setTrainAddLists(trainQueryAllLists);
        System.out.println(trainQueryAllLists);
        return trainQueryAllLists;
    }
}
