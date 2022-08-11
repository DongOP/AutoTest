package testcase;

import org.tcl.autotest.utils.SendEmailUtils;
import org.testng.annotations.Test;

public class TestEmail {

    @Test(description = "测试邮件发送")
    public void testSendMail(){
        String htmlPath = "D:\\Work\\APITest\\AutoTest\\AutoAPITest\\report\\1660201108354.html";
        String emailMsg = "各位同事，大家好！详细报告请查看附件的 html 文件，建议使用 Chrome 打开。";
        String emailAddress = "876873343@qq.com,dongdong1.zhan@tcl.com";
        String emailTitle = "【业务数字化项目】接口自动化测试报告";
        SendEmailUtils.sendMail(emailAddress, emailTitle,emailMsg, htmlPath);
//        System.out.println("emailMsg=" + emailMsg);
    }

}
