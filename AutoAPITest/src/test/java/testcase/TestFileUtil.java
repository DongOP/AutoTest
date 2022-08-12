package testcase;

import org.apache.log4j.Logger;
import org.tcl.autotest.utils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class TestFileUtil {

    private static Logger mLogger = Logger.getLogger(TestFileUtil.class.getClass());

    @Test(description = "测试 FileUtils", groups = {"TestFileUtil"}, dependsOnGroups = {"TestDemo"})
    public void testFileUtils() {
        String targetPath = FileUtils.findProjectFolder("\\report");
        String fileName = FileUtils.findNewestFile(targetPath, "html");
        System.out.println("最新的 HTML 文件=" + fileName);
        Assert.assertNotNull(fileName);
    }

}
