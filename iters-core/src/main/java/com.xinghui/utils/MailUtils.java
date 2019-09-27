package com.xinghui.utils;
import org.springframework.stereotype.Component;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * @Author:zhao-baolin
 * @Description: 邮件发送类
 * @Date:Created in 2018/4/17
 * @Modified By:
 */
@Component
public class MailUtils {
    /**
     * email:邮件发给谁  subject:主题  emailMsg：邮件的内容
     */
    public static void sendMail(String sender, String emailTitles, String emailContent)
            throws AddressException, MessagingException {
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");//发邮件的协议
        props.setProperty("mail.host", "smtp.qq.com");//发送邮件的服务器地址
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true
        props.setProperty("mail.username","1101225081@qq.com");
        props.setProperty("mail.password","glblvsldinymgbhi");
        Authenticator auth = new Authenticator() {// 创建验证器
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1101225081@qq.com", "glblvsldinymgbhi");//发邮件的账号的验证
            }
        };
        Session session = Session.getInstance(props, auth);
        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("1101225081@qq.com")); // 设置发送者
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(sender)); // 设置发送方式与接收者
        message.setSubject(emailTitles);//邮件的主题
        message.setContent(emailContent, "text/html;charset=utf-8");
        // 3.创建 Transport用于将邮件发送
        Transport.send(message);
    }

}
