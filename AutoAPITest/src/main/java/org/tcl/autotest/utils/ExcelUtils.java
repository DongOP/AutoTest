package org.tcl.autotest.utils;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

public class ExcelUtils {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 解析 excel 文件
     * @param sheetCount sheet页面，从1开始
     * @param rowNum 第几行，从1开始
     */
    public static Map readExcel(int sheetCount, int rowNum) {
        String excelPath = Constants.EXCEL_PATH;
        int fileNumList = 0;
        Map<Integer, Object> map = new HashMap<>();
        File excel = new File(excelPath);
        if (excel.isFile() && excel.exists()) {   //判断文件是否存在
            System.out.println("文件存在，进入excel解析阶段");
            String[] split = excel.getName().split("\\.");
            Workbook wb = null;
            try {
                FileInputStream fis = new FileInputStream(excel);
                //根据文件后缀 xls/xlsx 进行判断
                if ("xls".equals(split[1])) {
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(fis);
                } else {
                    return null;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("解析文件第几个sheet========" + sheetCount);
            // 开始解析
            Sheet sheet = wb.getSheetAt(sheetCount - 1);
            Row r = sheet.getRow(rowNum - 1); //读取表头信息，进行循环保存
            for (int j = 0; j < r.getLastCellNum(); j++) {
                String cellData = (String) getCellFormatValue(r.getCell(j));
                if (cellData != null || cellData.length() != 0) {
                    map.put(j, cellData.replaceAll(" ", ""));
                }
                //String sheetName = sheet.getSheetName();//sheet页名称
                //int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                //fileNumList.add(String.valueOf(sheet.getLastRowNum()));//获取行数
            }
        } else {
            System.out.println("未找到指定文件");
        }
        return map;
    }

    private static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            CellType cellType = CellType.forInt(cell.getCellType());
            switch (cellType) {
                case NUMERIC: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //excel文件内的日期列若设置单元格格式为日期，读取的值和文件内看到的不一样，
                        //需要根据num对应的格式进行转换，最好是直接在上传页面强制要求单元格格式为文本一劳永逸，
                        //因为num对应的日期格式不同版本的jar包不一样，成本太高但是工期够就无所谓了可慢慢研究。
                        short num = cell.getCellStyle().getDataFormat();
                        //String format = ExcelConstant.dateFormatMap.get(num);
                        SimpleDateFormat df = new SimpleDateFormat(String.valueOf(num));
                        cellValue = df.format(cell.getDateCellValue());
                    } else {
                        cell.setCellType(CellType.STRING);  //将数值型cell设置为string型
                        cellValue = cell.getStringCellValue();
                    }
                    break;
                }
                case FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }


}
