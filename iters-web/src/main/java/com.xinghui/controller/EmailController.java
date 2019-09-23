package com.xinghui.controller;
import com.xinghui.ResultDto;
import com.xinghui.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.xinghui.service.EmailService;
import com.xinghui.dot.EmailDot;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/email")
@SpringBootApplication
public class EmailController extends BaseController {
    @Autowired(required = false)
    private EmailService emailService;
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
    public ResultDto creatEmail(EmailDot emailDot) {
        System.out.println(emailService.creatEmail(emailDot));
        return success(emailService.creatEmail(emailDot));
    }
    /**
     * 邮箱导出功能
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
     * 邮箱查看详情
     * @return
     */
    @GetMapping(value = "/queryEmail/{id}")
    public ResultDto queryEmail(EmailDot emailDot,@PathVariable("id") String id) {
        List<EmailDot> emailListDots = emailService.queryEmail(emailDot,id);
        System.out.println("emailListDots:"+emailListDots);
        return success(emailListDots);
    }
    /**
     * 邮箱未读邮件查看详情
     * @return
     */
    @GetMapping(value = "/isMsgsEmail/{id}")
    public ResultDto isMsgsEmail(EmailDot emailDot,@PathVariable("id") String id) {
        Map<String,Object> emailListDots = emailService.isMsgsEmail(emailDot,id);
        System.out.println("emailListDots:"+emailListDots);
        return success(emailListDots);
    }
}
