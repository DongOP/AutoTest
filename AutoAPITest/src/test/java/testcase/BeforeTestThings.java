package testcase;

import org.tcl.autotest.utils.Constants;
import org.tcl.autotest.utils.FileUtils;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

public class BeforeTestThings {

    @BeforeTest
    public void clearLog(){
        String logPath = FileUtils.findProjectFolder(Constants.INFO_LOGO_NAME);
        FileUtils.ClearTxt(logPath);

        String errorLog = FileUtils.findProjectFolder(Constants.ERROR_LOGO_NAME);
        FileUtils.ClearTxt(errorLog);
    }


}
