package com.xinghui.serviceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.common.Constants;
import com.xinghui.dot.EmailDot;
import com.xinghui.dot.EmailQueryDot;
import com.xinghui.entity.Email;
import com.xinghui.mapper.EmailMapper;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
        for (Email emails : emailLists) {
            if (emails.getIsMsg() == Constants.emailStatus.FALSE) {
                emails.setIsMsgs(Constants.emailStatus.map.get(Constants.emailStatus.FALSE));
            } else {
                emails.setIsMsgs(Constants.emailStatus.map.get(Constants.emailStatus.TRUE));
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
        System.out.println(emailPageLists);
        return emailPageLists;
    }
    @Override
    public List<EmailDot> emailInBoxSumOrCount(EmailDot emailDot) {
        Map<String, Object> emailCountsOrSums = baseMapper.emailCountOrSum(emailDot);
        Map<String, Object> emailCountsOrSumsLists = new HashMap<>();
        List emailCountsOrSumsList = new ArrayList();
        emailCountsOrSumsLists.put("inBoxSum", emailCountsOrSums.get("inBoxSum"));
        emailCountsOrSumsLists.put("inBoxCount", emailCountsOrSums.get("inBoxCount"));
        Iterator<Map.Entry<String, Object>> it = emailCountsOrSumsLists.entrySet().iterator();
        while (it.hasNext()) {
            int emailOtherTime = 0;
            for (int emailInt = 0; emailInt <= 1; emailInt++) {
                int emailOtherTimes = emailOtherTime + 1;
                if (emailOtherTimes <= 1 && emailTimes == false) {
                    Map.Entry<String, Object> entry = it.next();
                    emailCountsOrSumsList.add(entry.getValue());
                } else if (emailOtherTimes > 1) {
                    emailTimes = true;
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
            if (!StringUtils.isEmpty(emailDots.getSendTime())) {
                //format()方法将Date转换成指定格式的String
                String sendTimess = format.format(emailDots.getSendTime());
                emailDots.setSendTimes(sendTimess);
            }
            journalDot.setEmailExportLists(exportEmailLists);
        }
        System.out.println(exportEmailLists);
        return exportEmailLists;
    }
    @Override
    public Map<String, Object> creatEmail(EmailDot emailDot) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        Map<String, Object> emailsAddsLists = baseMapper.creatEmail(emailDot);
        return emailsAddsLists;
    }
    @Override
    public List<EmailDot> queryEmail(EmailDot emailDot, @PathVariable("id") String id) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        List<EmailDot> emailqueryLists = baseMapper.queryEmail(emailDot, id);
        List<EmailDot> emailqueryOnlyLists = null;
        int emailCount = 0;
        List<EmailDot> emailqueryAllLists = new ArrayList<>();
        for (EmailDot emailDotsList : emailqueryLists) {
            emailCount = emailCount + 1;
            if (id.equals(emailDotsList.getId())) {
                if (emailDotsList.getIsMsg() == 1) {
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryLists.add(emailDotsList);
                } else if (emailDotsList.getIsMsg() == 0) {
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.TRUE);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryLists.add(emailDotsList);
                }
                emailqueryOnlyLists = emailqueryLists;
                EmailDot emailDotsLists = emailqueryOnlyLists.get(emailCount - 1);
                emailqueryAllLists.add(emailDotsLists);
                break;
            }
        }
        email.setEmailAddList(emailqueryAllLists);
        return emailqueryAllLists;
    }
    @Override
    public Map<String, Object> isMsgsEmail(EmailDot emailDot, @PathVariable("id") String id) {
        Map<String, Object> isMsgsMap = baseMapper.isMsgsEmail(emailDot, id);
        return isMsgsMap;
    }
    @Override
    public Page<Email> emailRecyclePage(Integer offset, Integer limit, EmailDot emailDot) {
        Email email = new Email();
        Page<Email> pages = new Page<>(offset, limit);
        BeanUtils.copyProperties(emailDot, email);
        List<Email> emailRecycleLists = baseMapper.emailRecyclePage(pages, emailDot, SecurityUtils.getCurrentUserId());
        for (Email emails:emailRecycleLists){
            if (emails.getIsMsg()==1) {
                String Msg = Constants.emailStatus.map.get(Constants.emailStatus.TRUE);
                emails.setIsMsgs(Msg);
            }else if(emails.getIsMsg()==0){
                String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
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
                if (emailDotsList.getIsMsg()==1) {
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryRecycleLists.add(emailDotsList);
                }else if(emailDotsList.getIsMsg()==0){
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryRecycleLists.add(emailDotsList);
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
            }
            journalDot.setEmailExportLists(exportEmailRecycleLists);
            System.out.println(exportEmailRecycleLists);
        }
        return exportEmailRecycleLists;
    }
    @Override
    public EmailQueryDot emailsRecyclesInfmormations() {
        EmailQueryDot emailQueryDot = new EmailQueryDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        List<EmailDot> emailRecycleLists = baseMapper.emailsRecyclesInfmormations();
        if (!CollectionUtils.isEmpty(emailRecycleLists)) {
            int emailRecycleTimes=0;
            for (EmailDot emailDots : emailRecycleLists) {
                emailRecycleTimes = emailRecycleTimes + 1;
                if (!StringUtils.isEmpty(emailDots.getSendTime())) {
                    //format()方法将Date转换成指定格式的String
                    String sendTimess = format.format(emailDots.getSendTime());
                    emailDots.setSendTimes(sendTimess);
                    emailQueryDot.setSendTime(sendTimess);
                }
                    if (!StringUtils.isEmpty(emailDots.getEmailContent())) {
                        if (emailDots.getEmailContent().length() <= 10) {
                            String emailContents = emailDots.getEmailContent();
                            emailDots.setEmailContent(emailContents);
                            emailQueryDot.setEmailContent(emailContents);
                        } else {
                            String emailContents = emailDots.getEmailContent().substring(0, 10) + "...";
                            emailDots.setEmailContent(emailContents);
                            emailQueryDot.setEmailContent(emailContents);
                        }
                        if (!StringUtils.isEmpty(emailDots.getEmailTitle())) {
                            if(emailDots.getEmailTitle().length()<=8){
                                String emailTitles = emailDots.getEmailTitle();
                                emailDots.setEmailTitle(emailTitles);
                                emailQueryDot.setEmailTitle(emailTitles);
                            }else{
                                String emailsTitles = emailDots.getEmailTitle().substring(0,8)+"...";
                                emailDots.setEmailTitle(emailsTitles);
                                emailQueryDot.setEmailTitle(emailsTitles);
                                System.out.println(emailRecycleTimes);
                            }
                    }
                }
                emailQueryDot.setEmailQueryDotList(emailRecycleLists);
            }
        }
        return emailQueryDot;
    }
    @Override
    public EmailQueryDot emailsReceiversInfmormations() {
        List<EmailDot> emailReceiversLists = baseMapper.emailsReceiversInfmormations();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        EmailQueryDot emailReceiversDots = new EmailQueryDot();
        if (!CollectionUtils.isEmpty(emailReceiversLists)) {
            int emailRecycleTimes = 0;
            for (EmailDot emailDots : emailReceiversLists) {
                emailRecycleTimes = emailRecycleTimes + 1;
                if (!StringUtils.isEmpty(emailDots.getSendTime())) {
                    //format()方法将Date转换成指定格式的String
                    String sendTimess = format.format(emailDots.getSendTime());
                    emailDots.setSendTimes(sendTimess);
                    emailReceiversDots.setSendTime(sendTimess);
                }
                if (!StringUtils.isEmpty(emailDots.getEmailTitle())) {
                    if (emailDots.getEmailTitle().length() <= 8) {
                        String emailTitles = emailDots.getEmailTitle();
                        emailDots.setEmailTitle(emailTitles);
                        emailReceiversDots.setEmailTitle(emailTitles);
                    } else {
                        String emailsTitles = emailDots.getEmailTitle().substring(0, 8) + "...";
                        emailDots.setEmailTitle(emailsTitles);
                        emailReceiversDots.setEmailTitle(emailsTitles);
                    }
                    if (!StringUtils.isEmpty(emailDots.getEmailContent())) {
                        if (emailDots.getEmailContent().length() <= 10) {
                            String emailContents = emailDots.getEmailContent();
                            emailDots.setEmailContent(emailContents);
                            emailReceiversDots.setEmailContent(emailContents);
                        } else {
                            String emailContents = emailDots.getEmailContent().substring(0, 10) + "...";
                            emailDots.setEmailContent(emailContents);
                            emailReceiversDots.setEmailContent(emailContents);
                            System.out.println(emailReceiversDots);
                        }
                    }
                    emailReceiversDots.setEmailReceiversLists(emailReceiversLists);
                }
            }
        }
            return emailReceiversDots;
    }
    @Override
    public Page<Email> emailSenterPage(Integer offset, Integer limit, EmailDot emailDot) {
        Email email = new Email();
        Page<Email> page = new Page<>(offset, limit);
        BeanUtils.copyProperties(emailDot, email);
        List<Email> emailSenterLists = baseMapper.emailSenterPage(page, emailDot, SecurityUtils.getCurrentUserId());
        for (Email emails : emailSenterLists) {
            if (emails.getIsMsg() == Constants.emailStatus.FALSE) {
                emails.setIsMsgs(Constants.emailStatus.map.get(Constants.emailStatus.FALSE));
            } else {
                emails.setIsMsgs(Constants.emailStatus.map.get(Constants.emailStatus.TRUE));
            }
        }
        if (!StringUtils.isEmpty(emailDot.getSendTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                emailDot.setSendTime(format.parse(emailDot.getSendTimes()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(emailSenterLists);
        }
        Page<Email> emailSenterPageLists = page.setRecords(emailSenterLists);
        return emailSenterPageLists;
    }
    @Override
    public EmailQueryDot emailsSentersInfmormations() {
        EmailQueryDot emailSentersDots = new EmailQueryDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        List<EmailDot> emailSentersLists = baseMapper.emailsSentersInfmormations();
        if (!CollectionUtils.isEmpty(emailSentersLists)) {
            int emailSentersTimes = 0;
            for (EmailDot emailDots : emailSentersLists) {
                emailSentersTimes = emailSentersTimes + 1;
                if (!StringUtils.isEmpty(emailDots.getSendTime())) {
                    //format()方法将Date转换成指定格式的String
                    String sendTimess = format.format(emailDots.getSendTime());
                    System.out.println(sendTimess);
                    emailDots.setSendTimes(sendTimess);
                    emailSentersDots.setSendTime(sendTimess);
                }
                if (!StringUtils.isEmpty(emailDots.getEmailTitle())) {
                    if (emailDots.getEmailTitle().length() <= 8) {
                        String emailTitles = emailDots.getEmailTitle();
                        emailDots.setEmailTitle(emailTitles);
                        emailSentersDots.setEmailTitle(emailTitles);
                    } else {
                        String emailsTitles = emailDots.getEmailTitle().substring(0, 8) + "...";
                        emailDots.setEmailTitle(emailsTitles);
                        System.out.println(emailSentersDots);
                        emailSentersDots.setEmailTitle(emailsTitles);
                    }
                    if (!StringUtils.isEmpty(emailDots.getEmailContent())) {
                        if (emailDots.getEmailContent().length() <= 10) {
                            String emailContents = emailDots.getEmailContent();
                            emailDots.setEmailContent(emailContents);
                            emailSentersDots.setEmailContent(emailContents);
                        } else {
                            String emailContents = emailDots.getEmailContent().substring(0, 10) + "...";
                            emailSentersDots.setEmailContent(emailContents);
                            System.out.println(emailSentersDots);
                            emailDots.setEmailContent(emailContents);

                        }
                    }
                    emailSentersDots.setEmailSentersLists(emailSentersLists);
                }
            }
        }
        return emailSentersDots;
    }
    @Override
    public List<EmailDot> emailSenterSumOrCount(EmailDot emailDot) {
        Map<String, Object> emailSenterCountsOrSums = baseMapper.emailSenterSumOrCount(emailDot);
        List emailSenterCountsOrSumsList = new ArrayList();
        Map<String, Object> emailSenterCountsOrSumsLists = new HashMap<>();
        emailSenterCountsOrSumsLists.put("inBoxCount", emailSenterCountsOrSums.get("inBoxCount"));
        emailSenterCountsOrSumsLists.put("inBoxSum", emailSenterCountsOrSums.get("inBoxSum"));
        Iterator<Map.Entry<String, Object>> it = emailSenterCountsOrSumsLists.entrySet().iterator();
        while (it.hasNext()) {
            int emailOtherTime = 0;
            for (int emailInt = 0; emailInt <= 1; emailInt++) {
                int emailOtherTimes = emailOtherTime + 1;
                if (emailOtherTimes <= 1 && emailTimes == false) {
                    Map.Entry<String, Object> entry = it.next();
                    emailSenterCountsOrSumsList.add(entry.getValue());
                } else if (emailOtherTimes > 1) {
                    emailTimes = true;
                    System.out.println(emailOtherTime);
                }
            }
        }
        return emailSenterCountsOrSumsList;
    }
    @Override
    public List<EmailDot> getSenterEmailList() {
        List<EmailDot> exportEmailSenterLists = baseMapper.getSenterEmailList();
        EmailDot journalDot = new EmailDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        for (EmailDot emailDots : exportEmailSenterLists) {
            if (!StringUtils.isEmpty(emailDots.getSendTime())){
                //format()方法将Date转换成指定格式的String
                String sendTimess = format.format(emailDots.getSendTime());
                emailDots.setSendTimes(sendTimess);
                System.out.println(sendTimess);
            }
            journalDot.setEmailExportLists(exportEmailSenterLists);

        }
        return exportEmailSenterLists;
    }
    @Override
    public List<EmailDot> querySenterEmail(EmailDot emailDot,@PathVariable("id") String id) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        List<EmailDot> emailquerySenterLists = baseMapper.querySenterEmail(emailDot, id);
        List<EmailDot>  emailqueryAllSenterLists = new ArrayList<> ();
        List<EmailDot> emailqueryRecycleOnlyLists = null ;
        int emailCount=0;
        for(EmailDot emailDotsList:emailquerySenterLists){
            emailCount=emailCount+1;
            if (id.equals(emailDotsList.getId())) {
                if (emailDotsList.getIsMsg()==1) {
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
                    emailDotsList.setIsMsgs(Msg);
                    emailquerySenterLists.add(emailDotsList);
                }else if(emailDotsList.getIsMsg()==0){
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
                    emailDotsList.setIsMsgs(Msg);
                    emailquerySenterLists.add(emailDotsList);
                    System.out.println(emailDotsList);
                }
                emailqueryRecycleOnlyLists=emailquerySenterLists;
                EmailDot emailDotsLists = emailqueryRecycleOnlyLists.get(emailCount-1);
                emailqueryAllSenterLists.add(emailDotsLists);
                break;
            }
        }
        email.setEmailAddList(emailqueryAllSenterLists);
        return emailqueryAllSenterLists;
    }
    @Override
    public Page<Email> emailDraftsPage(Integer offset, Integer limit, EmailDot emailDot) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        Page<Email> page = new Page<>(offset, limit);
        List<Email> emailDraftsLists = baseMapper.emailDraftsPage(page, emailDot, SecurityUtils.getCurrentUserId());
        for (Email emails : emailDraftsLists) {
            if (emails.getIsMsg() == Constants.emailStatus.FALSE) {
                emails.setIsMsgs(Constants.emailStatus.map.get(Constants.emailStatus.FALSE));
            } else {
                emails.setIsMsgs(Constants.emailStatus.map.get(Constants.emailStatus.TRUE));
            }
        }
        if (!StringUtils.isEmpty(emailDot.getSendTimes())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                emailDot.setSendTime(format.parse(emailDot.getSendTimes()));
                System.out.println(emailDraftsLists);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Page<Email> emailDraftsPageLists = page.setRecords(emailDraftsLists);
        return emailDraftsPageLists;
    }
    public List<EmailDot> emailDraftsEmail(EmailDot emailDot,@PathVariable("id") String id){
        EmailDot emailsDot = new EmailDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        List<EmailDot> emailDraftsLists = baseMapper.emailDraftsEmail(emailDot,id);
        for(EmailDot emailDots:emailDraftsLists){
            if (!StringUtils.isEmpty(emailDots.getSendTime())){
                //format()方法将Date转换成指定格式的String
                String sendTimess = format.format(emailDots.getSendTime());
                emailDots.setSendTimes(sendTimess);
                System.out.println(sendTimess);
            }
            if (id.equals(emailDots.getId())){
                if (emailDots.getStatus()==Constants.emailsStatus.FALSE){
                    emailDots.setStatus(Constants.emailsStatus.map.get(Constants.emailsStatus.FALSE));
                    emailsDot.setStatus(Constants.emailsStatus.map.get(Constants.emailsStatus.FALSE));
                }
            }
        }
        return emailDraftsLists;
    }
    @Override
    public List<EmailDot> emailDraftsSumOrCount(EmailDot emailDot) {
        Map<String, Object> emailDraftsCountsOrSums = baseMapper.emailDraftsSumOrCount(emailDot);
        Map<String, Object> emailDraftsCountsOrSumsLists = new HashMap<>();
        List emailDraftsCountsOrSumsList = new ArrayList();
        emailDraftsCountsOrSumsLists.put("DraftsSum", emailDraftsCountsOrSums.get("DraftsSum"));
        emailDraftsCountsOrSumsLists.put("DraftsCount", emailDraftsCountsOrSums.get("DraftsCount"));
        Iterator<Map.Entry<String, Object>> it = emailDraftsCountsOrSumsLists.entrySet().iterator();
        while (it.hasNext()) {
            int emailOtherTime = 0;
            for (int emailInt = 0; emailInt <= 1; emailInt++) {
                int emailOtherTimes = emailOtherTime + 1;
                if (emailOtherTimes <= 1 && emailTimes == false) {
                    Map.Entry<String, Object> entry = it.next();
                    emailDraftsCountsOrSumsList.add(entry.getValue());
                } else if (emailOtherTimes > 1) {
                    emailTimes = true;
                    System.out.println(true);
                }
            }
        }
        return emailDraftsCountsOrSumsList;
    }
    @Override
    public EmailQueryDot emailsDraftsInfmormations() {
        EmailQueryDot emailDraftsDots = new EmailQueryDot();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        List<EmailDot> emailDraftsLists = baseMapper.emailsDraftsInfmormations();
        if (!CollectionUtils.isEmpty(emailDraftsLists)) {
            int emailSentersTimes = 0;
            for (EmailDot emailDots : emailDraftsLists) {
                emailSentersTimes = emailSentersTimes + 1;
                if (!StringUtils.isEmpty(emailDots.getSendTime())) {
                    //format()方法将Date转换成指定格式的String
                    String sendTimess = format.format(emailDots.getSendTime());
                    emailDots.setSendTimes(sendTimess);
                    emailDraftsDots.setSendTime(sendTimess);
                }
                if (!StringUtils.isEmpty(emailDots.getEmailTitle())) {
                    if (emailDots.getEmailTitle().length() <= 8) {
                        String emailTitles = emailDots.getEmailTitle();
                        emailDots.setEmailTitle(emailTitles);
                        emailDraftsDots.setEmailTitle(emailTitles);
                        System.out.println(emailDraftsLists);
                    } else {
                        String emailsTitles = emailDots.getEmailTitle().substring(0, 8) + "...";
                        emailDots.setEmailTitle(emailsTitles);
                        emailDraftsDots.setEmailTitle(emailsTitles);
                    }
                    if (!StringUtils.isEmpty(emailDots.getEmailContent())) {
                        if (emailDots.getEmailContent().length() <= 10) {
                            String emailContents = emailDots.getEmailContent();
                            emailDots.setEmailContent(emailContents);
                            emailDraftsDots.setEmailContent(emailContents);
                            System.out.println(emailDraftsLists);
                        } else {
                            String emailContents = emailDots.getEmailContent().substring(0, 10) + "...";
                            emailDraftsDots.setEmailContent(emailContents);
                            emailDots.setEmailContent(emailContents);
                        }

                    }
                    emailDraftsDots.setEmailDraftsLists(emailDraftsLists);
                }
            }
        }
        return emailDraftsDots;
    }
    @Override
    public List<EmailDot> queryDraftsEmail(EmailDot emailDot,@PathVariable("id") String id) {
        Email email = new Email();
        List<EmailDot> emailqueryDraftsLists = baseMapper.queryDraftsEmail(emailDot, id);
        BeanUtils.copyProperties(emailDot, email);
        List<EmailDot> emailqueryDraftsOnlyLists = null ;
        List<EmailDot>  emailqueryAllDraftsLists = new ArrayList<> ();
        int emailCount=0;
        for(EmailDot emailDotsList:emailqueryDraftsLists){
            emailCount=emailCount+1;
            if (id.equals(emailDotsList.getId())) {
                if (emailDotsList.getIsMsg()==1) {
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
                    emailDotsList.setIsMsgs(Msg);
                    emailqueryDraftsLists.add(emailDotsList);
                }else if(emailDotsList.getIsMsg()==0){
                    String Msg = Constants.emailStatus.map.get(Constants.emailStatus.FALSE);
                    emailDotsList.setIsMsgs(Msg);
                    System.out.println(Msg);
                    emailqueryDraftsLists.add(emailDotsList);

                }
                emailqueryDraftsOnlyLists=emailqueryDraftsLists;
                EmailDot emailDotsLists = emailqueryDraftsOnlyLists.get(emailCount-1);
                emailqueryAllDraftsLists.add(emailDotsLists);
                break;
            }
        }
        email.setEmailAddList(emailqueryAllDraftsLists);
        return emailqueryAllDraftsLists;
    }
    @Override
    public List<EmailDot> getDraftsEmailList() {
        List<EmailDot> exportEmailDraftsLists = baseMapper.getDraftsEmailList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        EmailDot journalDot = new EmailDot();
        for (EmailDot emailDots : exportEmailDraftsLists) {
            if (!StringUtils.isEmpty(emailDots.getSendTime())){
                //format()方法将Date转换成指定格式的String
                String sendTimess = format.format(emailDots.getSendTime());
                emailDots.setSendTimes(sendTimess);
            }
            journalDot.setEmailExportLists(exportEmailDraftsLists);
        }
        return exportEmailDraftsLists;
    }
    @Override
    public Map<String, Object> draftsEmail(EmailDot emailDot) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDot, email);
        Map<String, Object> emailsAddsDraftsLists = baseMapper.draftsEmail(emailDot);
        return emailsAddsDraftsLists;
    }
}
