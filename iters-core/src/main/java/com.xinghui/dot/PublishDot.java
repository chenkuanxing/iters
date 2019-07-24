package com.xinghui.dot;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;

import java.util.Date;

@Data
public class PublishDot {

    /**
     * 编号
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 部门编号
     */
    private String depId;
    /**
     * 发布内容
     */
    private String content;
    /**
     * 发布人
     */
    private String publishBy;
    /**
     * 发布时间
     */
    private Date publishTime;

    private String publishTimes;
}
