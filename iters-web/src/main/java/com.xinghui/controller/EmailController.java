package com.xinghui.controller;
import com.xinghui.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xinghui.service.EmailService;
import com.xinghui.dot.EmailDot;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/email")
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
     * 添加
     * @return
     */
    @PostMapping(value = "/creatEmail")
    public ResultDto creatEmail(EmailDot emailDot) {
        System.out.println(emailDot);
        return success(emailService.creatEmail(emailDot));
    }
    /**
     * 删除
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
        //list转换为json
       List<EmailDot> emailListDots = emailService.queryEmail(emailDot,id);
       System.out.println("emailListDots:"+emailListDots);
        return success(emailListDots);
    }
}
