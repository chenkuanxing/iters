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
    @Override
    public Page<Email> emailRecyclePage(Integer offset, Integer limit, EmailDot emailDot) {
        Email email = new Email();
        Page<Email> pages = new Page<>(offset, limit);
        BeanUtils.copyProperties(emailDot, email);
        List<Email> emailRecycleLists = baseMapper.emailRecyclePage(pages, emailDot, SecurityUtils.getCurrentUserId());
        for (Email emails:emailRecycleLists){
            String MsgsRecycle = "是否";
            if (emails.getIsMsg()==1) {
                String Msg = MsgsRecycle.substring(1,2);
                emails.setIsMsgs(Msg);
            }else if(emails.getIsMsg()==0){
                String Msg = MsgsRecycle.substring(0,1);
                emails.setIsMsgs(Msg);
            }
            if (!StringUtils.isEmpty(emailDot.getSendTimes())) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    emailDot.setSendTime(format.parse(emailDot.getSendTimes()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(emailRecycleLists);
        Page<Email> emailPageLists = pages.setRecords(emailRecycleLists);
        return emailPageLists;
    }
    @Override
    public List<EmailDot> emailRecycleSumOrCount(EmailDot emailDot) {
        Map<String,Object> emailCountsOrRecycleSums = baseMapper.emailRecycleSumOrCount(emailDot);
        List emailCountsOrSumsRecycleList = new ArrayList();
        Map<String, Object> emailCountsOrSumsRecycleLists = new HashMap<>();
        emailCountsOrSumsRecycleLists.put("recycleSum",emailCountsOrRecycleSums.get("recycleSum"));
        emailCountsOrSumsRecycleLists.put("recycleCount",emailCountsOrRecycleSums.get("recycleCount"));
        System.out.println(emailCountsOrSumsRecycleLists);
        Iterator<Map.Entry<String, Object>> it = emailCountsOrSumsRecycleLists.entrySet().iterator();
        while (it.hasNext()) {
            int emailOtherTime=0;
            for (int emailInt=0;emailInt<=1;emailInt++){
                int emailOtherTimes=emailOtherTime+1;
                if (emailOtherTimes<=1&&emailTimes==false) {
                    System.out.println(emailOtherTime);
                    Map.Entry<String, Object> entry = it.next();
                    emailCountsOrSumsRecycleList.add(entry.getValue());
                }else if (emailOtherTimes>1){
                    emailTimes=true;
                }
            }
        }
        return emailCountsOrSumsRecycleList;
    }
    @Override
    public List<EmailDot> queryRecycleEmail(EmailDot emailDot,@PathVariable("id") String id) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        List<EmailDot> emailqueryRecycleLists = baseMapper.queryRecycleEmail(emailDot, id);
        List<EmailDot> emailqueryRecycleOnlyLists = null ;
        List<EmailDot>  emailqueryAllRecycleLists = new ArrayList<> ();
        int emailCount=0;
        for(EmailDot emailDotsList:emailqueryRecycleLists){
            emailCount=emailCount+1;
            if (id.equals(emailDotsList.getId())) {
                String Msgs = "否是";
                if (emailDotsList.getIsMsg()==1) {
                    String Msg = Msgs.substring(1,2);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryRecycleLists.add(emailDotsList);
                }else if(emailDotsList.getIsMsg()==0){
                    String Msg = Msgs.substring(1,2);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryRecycleLists.add(emailDotsList);
                    System.out.println(emailqueryRecycleLists);
                }
                emailqueryRecycleOnlyLists=emailqueryRecycleLists;
                EmailDot emailDotsLists = emailqueryRecycleOnlyLists.get(emailCount-1);
                emailqueryAllRecycleLists.add(emailDotsLists);
                break;
            }
        }
        email.setEmailAddList(emailqueryAllRecycleLists);
        return emailqueryAllRecycleLists;
    }
    @Override
    public List<EmailDot> emailRestoreRecycle(EmailDot emailDot,@PathVariable("id") String id) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        List<EmailDot> emailRestoreRecycleLists = baseMapper.emailRestoreRecycle(emailDot, id);
        return emailRestoreRecycleLists;
    }
    @Override
    public List<EmailDot> getRecycleEmailList() {
        List<EmailDot> exportEmailRecycleLists = baseMapper.getRecycleEmailList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        EmailDot journalDot = new EmailDot();
        for (EmailDot emailDots : exportEmailRecycleLists) {
            if (!StringUtils.isEmpty(emailDots.getSendTime())){
                //format()方法将Date转换成指定格式的String
                String sendTimess = format.format(emailDots.getSendTime());
                emailDots.setSendTimes(sendTimess);
                System.out.println("sendTimess:"+ sendTimess);
            }
            journalDot.setEmailExportLists(exportEmailRecycleLists);
            System.out.println(exportEmailRecycleLists);
        }
        return exportEmailRecycleLists;
    }
    @Override
    public List<EmailDot> getEmailsRecyclesInfmormations() {
        List<EmailDot> emailRecycleLists = baseMapper.getEmailsRecyclesInfmormations();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        List emailCountsOrSumsRecycleLists = new ArrayList();
        for (EmailDot emailDots : emailRecycleLists) {
            if (!StringUtils.isEmpty(emailDots.getSender())){
                String senders = emailDots.getSender();
                emailCountsOrSumsRecycleLists.add(senders);
            }
            if (!StringUtils.isEmpty(emailDots.getEmailTitle())){
                String emailTitles = emailDots.getEmailTitle();
                emailCountsOrSumsRecycleLists.add(emailTitles);
            }
            if (!StringUtils.isEmpty(emailDots.getSendTime())){
                //format()方法将Date转换成指定格式的String
                String sendTimess = format.format(emailDots.getSendTime());
                emailDots.setSendTimes(sendTimess);
                System.out.println("sendTimess:"+ sendTimess);
                emailCountsOrSumsRecycleLists.add(sendTimess);
            }
            if (!StringUtils.isEmpty(emailDots.getEmailContent())){
                String emailContents = emailDots.getEmailContent();
                emailCountsOrSumsRecycleLists.add(emailContents);
            }
        }
        System.out.println("emailCountsOrSumsRecycleLists:"+emailCountsOrSumsRecycleLists);
        return emailCountsOrSumsRecycleLists;
    }
}
