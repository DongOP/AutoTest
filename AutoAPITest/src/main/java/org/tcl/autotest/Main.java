package org.tcl.autotest;

import org.apache.log4j.Logger;

public class Main {
    // 使用 log4j 打印日志
    private static Logger mLogger = Logger.getLogger(Main.class.getClass());

    public static void main(String[] args) {
//        ExcelUtils.readExcel(1, 1);
        mLogger.info("----------- INFO --------");
        mLogger.error("----------- ERROR --------");
    }
}