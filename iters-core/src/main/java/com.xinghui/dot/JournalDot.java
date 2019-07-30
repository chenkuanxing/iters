package com.xinghui.dot;

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
     *发布内容
     */
    private String content;
    /**
     *执行时间
     */
    private Date performTime;

    /**
     *发布时间(String类型)
     */
    private String createdTimes;
    /**
     * 执行人
     */
    private String performBy;
    /**
     * 发布人姓名
     */
    private String publishName;
    /**
     * 执行人姓名
     */
    private String performByName;
}
