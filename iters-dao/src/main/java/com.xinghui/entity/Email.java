package com.xinghui.entity;
import cn.afterturn.easypoi.excel.annotation.Excel;
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
     * id
     */
    private String id;
    /**
     *邮件标示
     */
    private String imageId;
    /**
     * 发件人邮箱
     */
    @Excel(name = "发件人邮箱", orderNum = "1", width = 40, mergeVertical = true)
    private String sender;
    /**
     * 发件人姓名
     */
    @Excel(name = "发件人姓名", orderNum = "1", width = 40, mergeVertical = true)
    private String senderName;
    /**
     * 收件人邮箱
     */
    @Excel(name = "收件人邮箱", orderNum = "1", width = 40, mergeVertical = true)
    private String recipients;
    /**
     * 邮件标题
     */
    @Excel(name = "邮件标题", orderNum = "1", width = 40, mergeVertical = true)
    private String emailTitle;
    /**
     * 是否查看
     */
    @Excel(name = "是否查看", orderNum = "1", width = 40, mergeVertical = true)
    private String isMsgs;
    /**
     *发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sendTime;
    /**
     *发送时间
     */
    @Excel(name = "发送时间", orderNum = "1", width = 40, mergeVertical = true)
    private String sendTimes;
    /**
     * 是否查看
     */
    private Integer isMsg;
    /**
     * 附件内容
     */
    private String accessory;
    /**
     *邮件内容
     */
    @Excel(name = "邮件内容", orderNum = "1", width = 40, mergeVertical = true)
    private String emailContent;

    private List<EmailDot> emailAddList ;

    private List<EmailDot> emailAddLists;

    private List<EmailDot> emailExportLists;

}
