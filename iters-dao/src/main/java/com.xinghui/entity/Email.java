package com.xinghui.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinghui.dot.EmailDot;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@TableName("s_email")
public class Email extends BaseEntity {
    /**
     * 邮件标题
     */
    private String emailTitle;
    /**
     *邮件标示
     */
    private String imageId;
    /**
     * 发件人
     */
    private String sender;
    /**
     * 收件人
     */
    private String recipients;
    /**
     * 发件人姓名
     */
    private String senderName;
    /**
     *发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sendTime;
    /**
     *发送时间
     */
    private String sendTimes;
    /**
     * 是否查看
     */
    private Integer isMsg;
    /**
     * 是否查看
     */
    private String isMsgs;
    /**
     * 附件内容
     */
    private String accessory;
    /**
     *邮件内容
     */
    private String emailContent;

    private List<EmailDot> emailAddList ;

}
