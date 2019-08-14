package com.xinghui.dot;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class JournalDot {

    /**
     * 编号
     */
    private String id;
    /**
     * 日志标题
     */
    @Excel(name = "日志标题", orderNum = "1", width = 25, mergeVertical = true)
    private String title;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 发布时间
     */
    @Excel(name = "发布时间", orderNum = "1", width = 15, mergeVertical = true)
    private Date createdTime;
    /**
     * 发布内容
     */
    @Excel(name = "发布内容", orderNum = "1", width = 35, mergeVertical = true)
    private String content;
    /**
     * 执行时间
     */
    @Excel(name = "执行时间", orderNum = "1", width = 15, mergeVertical = true)
    private Date performTime;

    /**
     * 发布时间(String类型)
     */
    private String createdTimes;
    /**
     * 执行人
     */
    private String performBy;
    /**
     * 发布人姓名
     */
    @Excel(name = "发布人", orderNum = "1", width = 15, mergeVertical = true)
    private String publishName;
    /**
     * 执行人姓名
     */
    @Excel(name = "执行人", orderNum = "1", width = 15, mergeVertical = true)
    private String performByName;

}