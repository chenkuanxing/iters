package com.xinghui.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dot.EmailDot;
import com.xinghui.dot.EmailQueryDot;
import com.xinghui.entity.Email;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import java.util.List;


public interface EmailService extends IService<Email> {

    Page<Email> emailInboxPage(Integer offset, Integer limit, EmailDot emailDot);

    Map<String,Object> creatEmail(EmailDot emailDot);

    List<EmailDot> emailInBoxSumOrCount(EmailDot emailDot);

    List<EmailDot> getEmailList();

    List<EmailDot> queryEmail(EmailDot emailDot,@PathVariable("id") String id);

    Map<String,Object> isMsgsEmail(EmailDot emailDot,@PathVariable("id") String id);

    Page<Email> emailRecyclePage(Integer offset, Integer limit, EmailDot emailDot);

    List<EmailDot> emailRecycleSumOrCount(EmailDot emailDot);

    List<EmailDot> queryRecycleEmail(EmailDot emailDot,@PathVariable("id") String id);

    List<EmailDot> emailRestoreRecycle(EmailDot emailDot,@PathVariable("id") String id);

    List<EmailDot> getRecycleEmailList();

    EmailQueryDot emailsRecyclesInfmormations();

    EmailQueryDot emailsReceiversInfmormations();

    Page<Email> emailSenterPage(Integer offset, Integer limit, EmailDot emailDot);

    EmailQueryDot emailsSentersInfmormations();

    List<EmailDot> emailSenterSumOrCount(EmailDot emailDot);

    List<EmailDot> getSenterEmailList();

    List<EmailDot> querySenterEmail(EmailDot emailDot,@PathVariable("id") String id);

    Page<Email> emailDraftsPage(Integer offset, Integer limit, EmailDot emailDot);

    List<EmailDot> emailDraftsEmail( EmailDot emailDot,@PathVariable("id") String id);

    List<EmailDot> emailDraftsSumOrCount(EmailDot emailDot);

    EmailQueryDot emailsDraftsInfmormations();

    List<EmailDot> queryDraftsEmail(EmailDot emailDot,@PathVariable("id") String id);

    List<EmailDot> getDraftsEmailList();

    Map<String,Object> draftsEmail(EmailDot emailDot);

}
