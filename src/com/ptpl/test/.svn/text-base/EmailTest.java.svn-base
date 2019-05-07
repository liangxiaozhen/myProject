package com.ptpl.test;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 发送邮件的测试程序
 * 
 * @author lihs
 * 
 */
public class EmailTest {

    public static void main(String[] args) throws MessagingException, GeneralSecurityException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        
        /**
         * 网易(可用)
         */
      // 表示SMTP发送邮件，需要进行身份验证
       /*props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.163.com");
        // 发件人的账号
        props.put("mail.user", "15272772022@163.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "lhs0319");*/
        
        /**
         * QQ(失败)
         */       
/*        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        // 发件人的账号
        props.put("mail.user", "867065768@qq.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "iywvhukspjgnbeja");*/
        
        /**
         * 新浪(可用)
         */
        // 表示SMTP发送邮件，需要进行身份验证
        /*props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.sina.com");
        // 发件人的账号
        props.put("mail.user", "15272772022@sina.cn");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "lhs0319110");*/
        
        /**
         * 搜狐(失败)
         */
       /*// 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.sohu.com");
        // 发件人的账号
        props.put("mail.user", "15272772022@sohu.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "lhs0319110");*/
       /**
        * 139(可用)
        */
     // 表示SMTP发送邮件，需要进行身份验证
       /* props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.139.com");
        // 发件人的账号
        props.put("mail.user", "15272772022@139.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "lhs0319110");*/
        
        
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);
   
        // 设置收件人
        InternetAddress to = new InternetAddress("15272772022@163.com");
        message.setRecipient(RecipientType.TO, to);

        // 设置抄送
        InternetAddress cc = new InternetAddress("lhs0319110@163.com");
        message.setRecipient(RecipientType.CC, cc);

        // 设置密送，其他的收件人不能看到密送的邮件地址
        InternetAddress bcc = new InternetAddress("lhs0319110@163.com");
        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject("搜狐测试发送邮件");

        // 设置邮件的内容体
        message.setContent("132邮箱发往163邮箱，是否成功？", "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
    }
}