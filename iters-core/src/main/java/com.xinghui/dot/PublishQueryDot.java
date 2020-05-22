package com.xinghui.dot;

import lombok.Data;

import java.util.List;

@Data
public class PublishQueryDot {
    /**
     * 通知公告标题
     */
    private String title;
    /**
     * 通知公告发送时间
     */
    private String publishTime;

    private List<PublishDot> publishQueryDotList;

}
