package com.xinghui.dot;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RecodeDot {

    /**
     * 编号
     */
    private String id;
    /**
     * 定制任务标题
     */
    @Excel(name = "定制任务标题", orderNum = "1", width = 40, mergeVertical = true)
    private String title;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 发布时间
     */
    private Date createdTime;
    /**
     * 发布人姓名
     */
    @Excel(name = "发布人", orderNum = "1", width = 15, mergeVertical = true)
    private String publishName;
    /**
     * 发布时间(String类型)
     */
    @Excel(name = "发布时间", orderNum = "1", width = 15, mergeVertical = true)
    private String createdTimes;
    /**
     * 任务内容
     */
    @Excel(name = "任务内容", orderNum = "1", width = 55, mergeVertical = true)
    private String content;
//    /**
//     * 发布部门
//     */
//    @Excel(name = "发布部门", orderNum = "1", width = 15, mergeVertical = true)
//    private String publishDep;
    /**
     * 执行时间
     */
    private Date performTime;
    /**
     * 执行人姓名
     */
    @Excel(name = "执行人", orderNum = "1", width = 15, mergeVertical = true)
    private String performByName;
    /**
     * 执行时间(String类型)
     */
    @Excel(name = "执行时间", orderNum = "1", width = 15, mergeVertical = true)
    private String performTimes;
    /**
     * 执行人
     */
    private String performBy;

    private List<RecodeDot> exportList ;

    private boolean type;

}