package org.tcl.autotest.utils;

import org.apache.log4j.Logger;
import org.tcl.autotest.Main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class FileUtils {

    private static Logger mLogger = Logger.getLogger(Main.class.getClass());
    /**
     * 找出当前工程的绝对路径
     * @param path 文件路径
     */
    public static String findProjectFolder(String path) {
        File f = new File("");
        String courseFile = "";
        String absolutePath = "";
        try {
            courseFile = f.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!"".equals(courseFile)) {
            absolutePath = courseFile + path;
            return absolutePath;
        } else {
            mLogger.error("----------- ERROR -------- courseFile为空！");
            return "";
        }
    }

    /**
     * 找出目标文件夹下最新的文件(格式可自定义)
     * @param folderPath 文件所在的目录
     * @param fileSuffix 文件格式
     */
    public static String findNewestFile(String folderPath, String fileSuffix) {
        File file = new File(folderPath);
        File[] files = file.listFiles();
        // 按照目录中文件最后修改日期实现倒序排序
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return (int)(file2.lastModified() - file1.lastModified());
            }
        });
        // 取最新修改的文件，get文件名
        for (File f : files) {
            try {
                if (f.getName().contains(fileSuffix)) {
                    return f.getName();
                }
            } catch (Exception e) {
                mLogger.error("----------- ERROR --------" + e.toString());
            }
        }
        return "";
    }
}
