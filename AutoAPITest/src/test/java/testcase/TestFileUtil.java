package testcase;

import org.tcl.autotest.utils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFileUtil {

    @Test(groups = "TestFileUtil", dependsOnGroups = {"TestDemo"})
    private void testFileUtils() {
        String targetPath = FileUtils.findProjectFolder("\\report");
        String fileName = FileUtils.findNewestFile(targetPath, "html");
        System.out.println("最新的 HTML 文件=" + fileName);
        Assert.assertNotNull(fileName);
    }
}
