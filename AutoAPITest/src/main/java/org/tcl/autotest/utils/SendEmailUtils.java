package org.tcl.autotest.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailUtils {

    private static final SendEmailUtils mSendEmailUtils = new SendEmailUtils();
//    SEND_EMAIL_PASSWORD
    private SendEmailUtils(){}

    public static SendEmailUtils getInstance() {
        return mSendEmailUtils;
    }

    /**
     * 发送邮件
     * @param emailAddress 收件人的邮箱地址
     * @param emailTitle 邮件主题
     * @param emailMsg 邮件内容
     */
    public static void sendMail(String emailAddress, String emailTitle, String emailMsg)
            throws AddressException, MessagingException {
        // 1.[连接发件服务器]创建一个程序与发件人的 发送邮件服务器会话对象 Session
        Properties props = new Properties();
        // 邮件发送协议
        props.setProperty("mail.transport.protocol", "SMTP");
        // 邮件发送服务器的地址（如QQ邮箱的发件服务器地址SMTP服务器: smtp.qq.com）
        props.setProperty("mail.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");
        // 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人的用户名（不带后缀的，如QQ邮箱的@qq.com不用写）和授权码(这里一般不使用密码，为避免密码泄露，用授权码代替密码登录第三方邮件客户端)
                //授权码：用于登录第三方邮件客户端的专用密码。  第三方邮件客户端：如这个java程序。
                return new PasswordAuthentication(Constants.SEND_EMAIL_NAME, Constants.SEND_EMAIL_CODE);
            }
        };

        Session session = Session.getInstance(props, auth);
        // 2.[创建一封邮件]创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
        // 设置发送者的邮箱地址
        message.setFrom(new InternetAddress(Constants.SEND_EMAIL_ADDRESS));
        // 设置发送方式与接收者
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
        // 邮件主题
        message.setSubject(emailTitle);
        // 设置邮件的内容
        message.setContent(emailMsg, "text/html;charset=utf-8");
        // 3.[发送邮件]创建 Transport用于将邮件发送
        Transport.send(message);
    }


}
