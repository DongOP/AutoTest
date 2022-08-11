package testcase;

import org.tcl.autotest.utils.SendEmailUtils;
import org.testng.annotations.Test;

public class TestEmail {

    @Test(description = "测试邮件发送")
    public void testSendMail(){
        String htmlStr =
        try {
//            SendEmailUtils.sendMail("876873343@qq.com","【业务数字化项目】接口自动化测试报告","详细报告请查看html文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
