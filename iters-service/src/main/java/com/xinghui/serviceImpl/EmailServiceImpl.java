package com.xinghui.serviceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dot.EmailDot;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.EmailService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.xinghui.entity.Email;
import com.xinghui.mapper.EmailMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements EmailService {
    private boolean emailTimes = false;
    @Override
    public Page<Email> emailInboxPage(Integer offset, Integer limit, EmailDot emailDot) {
        Page<Email> page = new Page<>(offset, limit);
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
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
        Page<Email> emailPageLists = page.setRecords(emailLists);
        return emailPageLists;
    }
    @Override
    public List<EmailDot> emailInBoxSumOrCount(EmailDot emailDot) {
        Map<String,Object> emailCountsOrSums = baseMapper.emailCountOrSum(emailDot);
        Map<String, Object> emailCountsOrSumsLists = new HashMap<>();
        List emailCountsOrSumsList = new ArrayList();
        emailCountsOrSumsLists.put("inBoxSum",emailCountsOrSums.get("inBoxSum"));
        emailCountsOrSumsLists.put("inBoxCount",emailCountsOrSums.get("inBoxCount"));
        Iterator<Map.Entry<String, Object>> it = emailCountsOrSumsLists.entrySet().iterator();
        while (it.hasNext()) {
            int emailOtherTime=0;
            for (int emailInt=0;emailInt<=1;emailInt++){
                int emailOtherTimes=emailOtherTime+1;
                if (emailOtherTimes<=1&&emailTimes==false) {
                    Map.Entry<String, Object> entry = it.next();
                    emailCountsOrSumsList.add(entry.getValue());
                }else if (emailOtherTimes>1){
                    emailTimes=true;
                }
            }
        }
        return emailCountsOrSumsList;
    }
    @Override
    public List<EmailDot> getEmailList() {
        List<EmailDot> exportEmailLists = baseMapper.getEmailList();
        EmailDot journalDot = new EmailDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        for (EmailDot emailDots : exportEmailLists) {
            if (!StringUtils.isEmpty(emailDots.getSendTime())){
                //format()方法将Date转换成指定格式的String
                String sendTimess = format.format(emailDots.getSendTime());
                emailDots.setSendTimes(sendTimess);
                System.out.println("sendTimess:"+ sendTimess);
            }
            journalDot.setEmailExportLists(exportEmailLists);
        }
        System.out.println(exportEmailLists);
        return exportEmailLists;
    }
    @Override
    public Map<String,Object> creatEmail(EmailDot emailDot) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        Map<String,Object> emailsAddsLists = baseMapper.creatEmail(emailDot);
        System.out.println(emailsAddsLists);
        return emailsAddsLists;
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
                String Msgs = "否是";
                if (emailDotsList.getIsMsg()==1) {
                    String Msg = Msgs.substring(1,2);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryLists.add(emailDotsList);
                }else if(emailDotsList.getIsMsg()==0){
                    String Msg = Msgs.substring(1,2);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryLists.add(emailDotsList);
                }
                emailqueryOnlyLists=emailqueryLists;
                EmailDot emailDotsLists = emailqueryOnlyLists.get(emailCount-1);
                emailqueryAllLists.add(emailDotsLists);
                break;
            }
        }
        email.setEmailAddList(emailqueryAllLists);
        return emailqueryAllLists;
    }
    public Map<String,Object> isMsgsEmail(EmailDot emailDot,@PathVariable("id") String id) {
        Map<String,Object> isMsgsMap = baseMapper.isMsgsEmail(emailDot,id);
        System.out.println(isMsgsMap);
        return isMsgsMap;
    }
}
