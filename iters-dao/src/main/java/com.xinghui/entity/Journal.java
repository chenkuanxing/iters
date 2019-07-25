package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("s_journal")
public class Journal extends BaseEntity{
    /**
     * 日志标题
     */
    private String title;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 执行人
     */
    private String performBy;
    /**
     *发布内容
     */
    private String content;
    /**
     *执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date performTime;

    @TableField(exist = false)
    private String publisherName;
}
