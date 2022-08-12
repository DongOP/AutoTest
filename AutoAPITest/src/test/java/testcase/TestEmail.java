package testcase;

import org.apache.log4j.Logger;
import org.tcl.autotest.Main;
import org.tcl.autotest.utils.Constants;
import org.tcl.autotest.utils.FileUtils;
import org.tcl.autotest.utils.SendEmailUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class TestEmail {

    private static Logger mLogger = Logger.getLogger(TestEmail.class.getClass());

    // 发送油价需要在框架运行完之后才有最新的报告生成
    @AfterSuite
    public void testSendMail(){
        String emailMsg = "各位同事，大家好！详细报告请查看附件的 html 文件，建议使用 Chrome 打开。";
//        String toAddress = "876873343@qq.com,donking0030@gmail.com";
//        String ccAddress = "dongjava@outlook.com";
        String emailTitle = "【业务数字化项目】接口自动化测试报告";
        // 获取最新的 HTML 文件（实际是上一次执行结果的报告文件）
        String targetPath = FileUtils.findProjectFolder("\\report");
        String htmlPath = targetPath + "\\" + Constants.TEST_REPORT_NAME;
        Reporter.log("testSendMail 测试 htmlPath=" + htmlPath);
        boolean checkSendEmail = SendEmailUtils.getInstance().sendMail("876873343@qq.com", "", emailTitle, emailMsg, htmlPath);
        mLogger.error("testSendMail 测试 checkSendEmail=" + checkSendEmail);
        Assert.assertTrue(checkSendEmail);
    }

}
