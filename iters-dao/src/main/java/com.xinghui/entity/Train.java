package com.xinghui.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinghui.dot.TrainDot;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("s_train")
public class Train extends BaseEntity {
    /**
     * id
     */
    @Excel(name = "编号", orderNum = "1", width = 28, mergeVertical = true)
    private String id;
    /**
     *培训名称
     */
    @Excel(name = "培训名称", orderNum = "1", width = 28, mergeVertical = true)
    private String trainingName;
    /**
     * 主讲人
     */
    @Excel(name = "主讲人", orderNum = "1", width = 28, mergeVertical = true)
    private String keynoteSpeaker;
    /**
     *培训时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date trainTime;
    /**
     *培训时间
     */
    @Excel(name = "培训时间", orderNum = "1", width = 18, mergeVertical = true)
    private String trainTimes;
    /**
     *结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    /**
     *结束时间
     */
    @Excel(name = "结束时间", orderNum = "1", width = 18, mergeVertical = true)
    private String endTimes;
    /**
     *参加人
     */
    @Excel(name = "参加人", orderNum = "1", width = 36, mergeVertical = true)
    private String participator;
    /**
     *培训地点
     */
    @Excel(name = "培训地点", orderNum = "1", width = 36, mergeVertical = true)
    private String trainingPlace;
    /**
     *成绩
     */
    @Excel(name = "成绩", orderNum = "1", width = 36, mergeVertical = true)
    private Integer performance;
    /**
     *培训内容
     */
    @Excel(name = "培训内容", orderNum = "1", width = 36, mergeVertical = true)
    private String trainContent;

    private List<TrainDot> trainAddLists;
}
