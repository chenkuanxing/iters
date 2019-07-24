package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("s_publish")
public class Publish extends BaseEntity {
    /**
     * 标题
     */
    private String title;
    /**
     * 部门编号
     */
    private String depId;
    /**
     * 内容
     */
    private String content;
    /**
     * 发布人
     */
    private String publishBy;
    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;

    @TableField(exist = false)
    private String publishName;
}
