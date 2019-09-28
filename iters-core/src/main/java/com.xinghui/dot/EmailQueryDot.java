package com.xinghui.dot;
import lombok.Data;
import java.util.List;
@Data
public class EmailQueryDot {
    /**
     * 收件人
     */
    private String sendName;
    /**
     * 邮箱标题
     */
    private String emailTitle;
    /**
     * 邮箱发件时间
     */
    private String sendTime;
    /**
     * 邮箱内容
     */
    private String emailContent;

    private List<EmailDot> emailQueryDotList;

    private List<EmailDot> emailReceiversLists;
}
