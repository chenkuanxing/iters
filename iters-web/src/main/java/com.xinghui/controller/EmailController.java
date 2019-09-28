package com.xinghui.controller;
import com.xinghui.ResultDto;
import com.xinghui.utils.ExcelUtils;
import com.xinghui.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import com.xinghui.service.EmailService;
import com.xinghui.dot.EmailDot;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/email")
public class EmailController extends BaseController {
    @Autowired(required = false)
    private EmailService emailService;
    @Autowired(required = false)
    private JavaMailSender mailSender;
    /**
     * 查询邮箱收件箱列表
     */
    @GetMapping(value = "/emailInboxPage")
    public ResultDto emailInboxPage(Integer offset, Integer limit, EmailDot emailDot) {
        return success(emailService.emailInboxPage(offset, limit, emailDot));
    }
    /**
     * 查询邮箱收件箱数目未读邮件数目
     */
    @GetMapping(value = "/emailInBoxSumOrCount")
    public ResultDto emailInBoxSumOrCount(EmailDot emailDot) {
        return success(emailService.emailInBoxSumOrCount(emailDot));
    }
    /**
     * 添加收件箱内容
     * @return
     */
    @PostMapping(value = "/creatEmail")
    public ResultDto creatEmail(EmailDot emailDot,String sender,String recipients,String emailTitles,String emailContent){
            SimpleMailMessage message = new SimpleMailMessage();
            sender=emailDot.getSender();
            message.setFrom(sender);
            recipients=emailDot.getRecipients();
            message.setTo(recipients);
            emailTitles=emailDot.getEmailTitle();
            message.setSubject(emailTitles);
            emailContent=emailDot.getEmailContent();
            message.setText(emailContent);
            System.out.println(sender);
        try {
            MailUtils.sendMail(sender,emailTitles,emailContent);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("异常信息：" + "发送失败");
        }
        System.out.println(emailService.creatEmail(emailDot));
        return success(emailService.creatEmail(emailDot));
    }
    /**
     * 收件箱邮箱导出功能
     * @return
     */
    @GetMapping(value = "/exportEmailsInformation")
    public void exportEmailsInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<EmailDot> emailDotsList = emailService.getEmailList();
        ExcelUtils.exportExcel(emailDotsList, "收件箱邮箱详情", "收件箱邮箱详情", EmailDot.class, "收件箱邮箱详情.xls", response);
    }
    /**
     * 删除收件箱内容
     * @return
     */
    @GetMapping(value = "/deleteEmail/{id}")
    private ResultDto deleteEmail(@PathVariable("id") String id) {
        return success(emailService.removeById(id));
    }
    /**
     * 收件箱邮箱查看详情
     * @return
     */
    @GetMapping(value = "/queryEmail/{id}")
    public ResultDto queryEmail(EmailDot emailDot,@PathVariable("id") String id) {
        List<EmailDot> emailListDots = emailService.queryEmail(emailDot,id);
        System.out.println("emailListDots:"+emailListDots);
        return success(emailListDots);
    }
    /**
     * 收件箱邮箱未读邮件查看详情
     * @return
     */
    @GetMapping(value = "/isMsgsEmail/{id}")
    public ResultDto isMsgsEmail(EmailDot emailDot,@PathVariable("id") String id) {
        Map<String,Object> emailListDots = emailService.isMsgsEmail(emailDot,id);
        System.out.println("emailListDots:"+emailListDots);
        return success(emailListDots);
    }
    /**
     * 查询邮箱回收站列表
     */
    @GetMapping(value = "/emailRecyclePage")
    public ResultDto emailRecyclePage(Integer offset, Integer limit, EmailDot emailDot) {
        return success(emailService.emailRecyclePage(offset, limit, emailDot));
    }
    /**
     * 查询邮箱回收站数目未读邮件数目
     */
    @GetMapping(value = "/emailRecycleSumOrCount")
    public ResultDto emailRecycleSumOrCount(EmailDot emailDot) {
        return success(emailService.emailRecycleSumOrCount(emailDot));
    }
    /**
     * 回收站邮箱查看详情
     * @return
     */
    @GetMapping(value = "/queryRecycleEmail/{id}")
    public ResultDto queryRecycleEmail(EmailDot emailDot,@PathVariable("id") String id) {
        List<EmailDot> emailListRecycleDots = emailService.queryRecycleEmail(emailDot,id);
        System.out.println("emailListRecycleDots:"+emailListRecycleDots);
        return success(emailListRecycleDots);
    }
    /**
     * 回收站邮箱还原详情
     * @return
     */
    @GetMapping(value = "/emailRestoreRecycle/{id}")
    public ResultDto emailRestoreRecycle(EmailDot emailDot,@PathVariable("id") String id) {
        List<EmailDot> emailListRestoreRecycleDots = emailService.emailRestoreRecycle(emailDot,id);
        System.out.println("emailListRestoreRecycleDots:"+emailListRestoreRecycleDots);
        return success(emailListRestoreRecycleDots);
    }
    /**
     * 回收站邮箱导出功能
     * @return
     */
    @GetMapping(value = "/exportRecycleEmails")
    public void exportRecycleEmails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<EmailDot> emailDotsRecycleList = emailService.getRecycleEmailList();
        ExcelUtils.exportExcel(emailDotsRecycleList, "回收站邮箱详情", "收件箱邮箱详情", EmailDot.class, "收件箱邮箱详情.xls", response);
    }
    /**
     * 回收站邮箱查询详情功能
     * @return
     */
    @GetMapping(value = "/emailsRecyclesInfmormations")
    public ResultDto emailsRecyclesInfmormations() {
        return success( emailService.emailsRecyclesInfmormations());
    }
    /**
     * 收件箱邮箱查询详情功能
     * @return
     */
    @GetMapping(value = "/emailsReceiversInfmormations")
    public ResultDto emailsReceiversInfmormations() {
        return success( emailService.emailsReceiversInfmormations());
    }

}
