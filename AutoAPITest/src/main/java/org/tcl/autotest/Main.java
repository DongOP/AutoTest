package org.tcl.autotest;

import org.apache.log4j.Logger;
import org.tcl.autotest.utils.FileUtils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;

public class Main {
    // 使用 log4j 打印日志
    private static Logger mLogger = Logger.getLogger(Main.class.getClass());

    public static void main(String[] args) {
//        mLogger.info("----------- INFO --------");
//        mLogger.error("----------- ERROR --------");
        String path = FileUtils.findProjectFolder("\\report\\log\\log.log");
//        boolean checkDel = FileUtils.deleteDir(new File(path));
//        D:\Work\APITest\AutoTest\AutoAPITest\report\log
        File file = new File(path);
        System.out.println("删除文件=" + path);
    }
}