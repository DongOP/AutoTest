package org.tcl.autotest.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEmailUtils {

    private static Logger mLogger = Logger.getLogger(SendEmailUtils.class.getClass());
    private static final SendEmailUtils mSendEmailUtils = new SendEmailUtils();

    private SendEmailUtils() {
    }

    public static SendEmailUtils getInstance() {
        return mSendEmailUtils;
    }

    // 创建验证器
//    private static Authenticator auth = new Authenticator() {
//        public PasswordAuthentication getPasswordAuthentication() {
//            //发件人的用户名（不带后缀的，如QQ邮箱的@qq.com不用写）和授权码(这里一般不使用密码，为避免密码泄露，用授权码代替密码登录第三方邮件客户端)
//            //授权码：用于登录第三方邮件客户端的专用密码。  第三方邮件客户端：如这个java程序。
//            return new PasswordAuthentication(Constants.SEND_EMAIL_NAME, Constants.SEND_EMAIL_CODE);
//        }
//    };

    /**
     * 发送邮件
     *
     * @param toAddress  收件人邮箱地址
     * @param ccAddress  抄送人邮箱地址
     * @param emailTitle 邮件主题
     * @param emailMsg   邮件内容
     * @param fileName   附件目录
     */
    public boolean sendMail(String toAddress, String ccAddress, String emailTitle, String emailMsg, String fileName) {
        //设置参数
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名  使用qq邮箱当作主机发送可选择
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");//设置认证
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 得到回话对象
        Session session = Session.getInstance(properties);
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 发件人
            message.setFrom(new InternetAddress(Constants.SEND_EMAIL_ADDRESS));
            // Set To: 收件人
            String[] strings = toAddress.split(",");
            //将收件人信息通过‘,’拆分多个邮件地址
            InternetAddress[] internetAddresses = new InternetAddress[strings.length];
            for (int i = 0; i < strings.length; i++) {
                internetAddresses[i] = new InternetAddress(strings[i]);
            }
            //添加收件人信息
            message.setRecipients(MimeMessage.RecipientType.TO, internetAddresses);
            if (!"".equals(ccAddress)) {
                // Set CC: 抄送人
                String[] cc = ccAddress.split(",");
                //将收件人信息通过‘,’拆分多个邮件地址
                InternetAddress[] ccAddresses = new InternetAddress[cc.length];
                for (int i = 0; i < cc.length; i++) {
                    ccAddresses[i] = new InternetAddress(cc[i]);
                }
                //添加收件人信息
                message.setRecipients(MimeMessage.RecipientType.CC, ccAddresses);
            }
            // Set Subject: 主题文字
            message.setSubject(emailTitle);
            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            messageBodyPart.setText(emailMsg);
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);
            // 附件部分
            messageBodyPart = new MimeBodyPart();
            // 设置要发送附件的文件路径
            DataSource source = new FileDataSource(fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            // messageBodyPart.setFileName(filename);
            // 处理附件名称中文（附带文件路径）乱码问题
            messageBodyPart.setFileName(MimeUtility.encodeText(Constants.MAIL_FILE_NAME));
            multipart.addBodyPart(messageBodyPart);
            // 发送完整消息
            message.setContent(multipart);
            Transport transport = session.getTransport();
            transport.connect(Constants.SEND_EMAIL_NAME, Constants.SEND_EMAIL_CODE);
            // 发送消息
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            mLogger.error("MessagingException===" + e.toString());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            mLogger.error("UnsupportedEncodingException===" + e.toString());
            e.printStackTrace();
        }
        return false;
    }


}
