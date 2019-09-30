package com.xinghui.mapper;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.dot.EmailDot;
import com.xinghui.entity.Email;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface EmailMapper extends BaseMapper<Email> {

    List<Email> emailInboxPage(Page page, @Param("emailDot") EmailDot emailDot, @Param("userId") String userId);

    Map<String,Object> emailCountOrSum(EmailDot emailDot);

    Map<String,Object> emailRecycleSumOrCount(EmailDot emailDot);

    Map<String,Object> creatEmail (EmailDot emailDot);

    List<EmailDot> getEmailList();

    List<EmailDot> queryEmail(EmailDot emailDot, @Param("id") String id);

    Map<String,Object> isMsgsEmail(EmailDot emailDot, @Param("id") String id);

    List<Email> emailRecyclePage(Page page, @Param("emailDot") EmailDot emailDot, @Param("userId") String userId);

    List<EmailDot> queryRecycleEmail(EmailDot emailDot, @Param("id") String id);

    List<EmailDot> emailRestoreRecycle(EmailDot emailDot, @Param("id") String id);

    List<EmailDot> getRecycleEmailList();

    List<EmailDot> emailsRecyclesInfmormations();

    List<EmailDot> emailsReceiversInfmormations();

    List<Email> emailSenterPage(Page page, @Param("emailDot") EmailDot emailDot, @Param("userId") String userId);

    List<EmailDot> emailsSentersInfmormations();

    Map<String,Object> emailSenterSumOrCount(EmailDot emailDot);

    List<EmailDot> getSenterEmailList();

    List<EmailDot> querySenterEmail(EmailDot emailDot, @Param("id") String id);

    List<Email> emailDraftsPage(Page page, @Param("emailDot") EmailDot emailDot, @Param("userId") String userId);

    List<EmailDot> emailDraftsEmail(EmailDot emailDot, @Param("id") String id);

    Map<String,Object> emailDraftsSumOrCount(EmailDot emailDot);

    List<EmailDot> emailsDraftsInfmormations();

    List<EmailDot> queryDraftsEmail(EmailDot emailDot, @Param("id") String id);

    List<EmailDot> getDraftsEmailList();

    Map<String,Object> draftsEmail(EmailDot emailDot);

}
