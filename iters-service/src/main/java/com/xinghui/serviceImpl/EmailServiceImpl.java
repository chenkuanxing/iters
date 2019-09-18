package com.xinghui.serviceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.EmailDot;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.xinghui.entity.Email;
import com.xinghui.mapper.EmailMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements EmailService {

    @Override
    public Page<Email> emailInboxPage(Integer offset, Integer limit, EmailDot emailDot) {
        Page<Email> page = new Page<>(offset, limit);
        List<Email> emailLists = baseMapper.emailInboxPage(page, emailDot, SecurityUtils.getCurrentUserId());
        for (Email emails:emailLists){
                String Msgs = "是否";
                if (emails.getIsMsg()==1) {
                    String Msg = Msgs.substring(1,2);
                    emails.setIsMsgs(Msg);
                }else if(emails.getIsMsg()==0){
                    String Msg = Msgs.substring(0,1);
                    emails.setIsMsgs(Msg);
                }
            }
        if (!StringUtils.isEmpty(emailDot.getSendTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                emailDot.setSendTime(format.parse(emailDot.getSendTimes()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println(emailLists);
        Page<Email> emailPageLists = page.setRecords(emailLists);
        return emailPageLists;
    }
    @Override
    public List<EmailDot> creatEmail(EmailDot emailDot) {
      Email email = new Email();
      BeanUtils.copyProperties(emailDot, email);
      List<EmailDot> emailAddLists = baseMapper.creatEmail(emailDot);
      System.out.println(emailAddLists);
      return emailAddLists;

    }
    @Override
    public List<EmailDot> queryEmail(EmailDot emailDot,@PathVariable("id") String id) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        List<EmailDot> emailqueryLists = baseMapper.queryEmail(emailDot, id);
        List<EmailDot> emailqueryOnlyLists = null ;
        int emailCount=0;
        List<EmailDot>  emailqueryAllLists = new ArrayList<> ();
        for(EmailDot emailDotsList:emailqueryLists){
            emailCount=emailCount+1;
            if (id.equals(emailDotsList.getId())) {
                emailqueryOnlyLists=emailqueryLists;
                EmailDot emailDotsLists = emailqueryOnlyLists.get(emailCount-1);
                emailqueryAllLists.add(emailDotsLists);
                break;
            }
        }
        email.setEmailAddList(emailqueryAllLists);
        System.out.println("emailqueryAllLists:"+emailqueryAllLists);
        return emailqueryAllLists;
    }



}
