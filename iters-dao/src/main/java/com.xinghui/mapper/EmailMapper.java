package com.xinghui.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.dot.EmailDot;
import com.xinghui.entity.Email;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface EmailMapper extends BaseMapper<Email> {

    List<Email> emailInboxPage(Page page, @Param("emailDot") EmailDot emailDot, @Param("userId") String userId);

    List<EmailDot> creatEmail(EmailDot emailDot);

    List<EmailDot> queryEmail(EmailDot emailDot, @Param("id") String id);
}
