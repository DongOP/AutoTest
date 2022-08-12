package testcase;

import org.tcl.autotest.utils.Constants;
import org.tcl.autotest.utils.FileUtils;
import org.testng.annotations.BeforeTest;

public class BeforeTestThings {

    @BeforeTest
    public void clearLog(){
        // log 配置：log4j.properties
        String logPath = FileUtils.findProjectFolder(Constants.INFO_LOGO_NAME);
        FileUtils.ClearTxt(logPath);

        String errorLog = FileUtils.findProjectFolder(Constants.ERROR_LOGO_NAME);
        FileUtils.ClearTxt(errorLog);
    }


}
