package com.yrp.write.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author: YeRenping
 * @Date: 2023/1/20
 * @Description:
 */
@Slf4j
public class ExcelReadTest {
    private static final String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/poi/";
    private FileInputStream     fis  = null;
    private FileOutputStream    fos  = null;

    /**
     * 创建一个03版本的excel
     */
    @Test
    public void testReadExcel03Version() {
        try {
            fis = new FileInputStream(new File(PATH + "是叶十三【03版本】.xls"));
            // 1、从磁盘中获取一个Excel工作簿
            Workbook workbook = new HSSFWorkbook(fis);
            // 2、获取该工作簿中的工作表
            Sheet sheetAt = workbook.getSheetAt(0);
            Row row = sheetAt.getRow(0);
            Cell cell = row.getCell(1);
            double numericCellValue = cell.getNumericCellValue();
            log.info("c1 ->{}", String.valueOf(numericCellValue));

            Row row2 = sheetAt.getRow(1);
            Cell cell2 = row2.getCell(1);
            String c2 = cell2.getStringCellValue();
            log.info("c2 ->{}", c2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("{}", "读取《是叶十三【03版本】.xls》 完毕!");
    }

    /**
     * 读取一个07版本的excel中的内容
     */
    @Test
    public void testReadExcel07Version() {
        try {
            fis = new FileInputStream(new File(PATH + "是叶十三【07版本】.xlsx"));
            // 1、从磁盘中获取一个Excel工作簿
            Workbook workbook = new XSSFWorkbook(fis);
            // 2、获取该工作簿中的工作表
            Sheet sheetAt = workbook.getSheetAt(0);
            Row row = sheetAt.getRow(0);
            Cell cell = row.getCell(1);
            double numericCellValue = cell.getNumericCellValue();
            log.info("c1 ->{}", String.valueOf(numericCellValue));

            Row row2 = sheetAt.getRow(1);
            Cell cell2 = row2.getCell(1);
            String c2 = cell2.getStringCellValue();
            log.info("c2 ->{}", c2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("{}", "读取《是叶十三【07版本】.xlsx》 完毕!");
    }

    /**
     * 测试Excel中不同类型数据的读取
     */
    @Test
    public void testReadExcelDiffrentTypes() {
        try {
            fis = new FileInputStream(new File(PATH + "19网工2班.xlsx"));
            // 1、从磁盘中获取一个Excel工作簿
            Workbook workbook = new XSSFWorkbook(fis);
            // 2、获取该工作簿中的工作表
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet != null){
                String sheetName = sheet.getSheetName();
                log.info("工作表的名字是-{}",sheetName);
                // 3、获取工作表标题
                Row rowTitle = sheet.getRow(0);
                // 获取第0行有效cell的个数
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum ++ ){
                    // 获取单元格
                    Cell cell = rowTitle.getCell(cellNum);
                    if (cell != null){
                        // 获取单元格的类型
                        int cellType = cell.getCellType();
                        // 输出单元格内容
                        String titleName = cell.getStringCellValue();
                        System.out.printf(titleName + " | ");
                    }
                }
                // 换行
                System.out.println();
                // 3、获取工作表的内容
                // 获取总行数
                int cellsCount = sheet.getPhysicalNumberOfRows();
                // 从第2行开始读取
                for (int rowNum = 1; rowNum < cellsCount; rowNum++) {
                    Row rowData = sheet.getRow(rowNum);
                    if (rowData!=null) {
                        // 读取列
                        int cellCount2 = rowData.getPhysicalNumberOfCells();
                        for (int cellNum = 0; cellNum < cellCount2; cellNum++) {
                            Cell cell = rowData.getCell(cellNum);
                            if (cell != null){
                                // 获取单元格的类型
                                int cellType = cell.getCellType();
                                matchPrint(cellType,cell);
                            }
                        }
                        System.out.println();
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("{}", "读取《是叶十三【07版本】.xlsx》 完毕!");
    }

    /**
     * 类型匹配后输出
     * @param cellType
     * @param cell
     */
    private void matchPrint(int cellType,Cell cell){
        switch (cellType) {
            // 字符串类型
            case Cell.CELL_TYPE_STRING:
                String stringCellValue = cell.getStringCellValue();
                System.out.print(stringCellValue + " | ");
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                boolean booleanCellValue = cell.getBooleanCellValue();
                System.out.print(booleanCellValue + " | ");
                break;
            // 空
            case Cell.CELL_TYPE_BLANK:
                break;
            // 数字类型（整数 小数 日期）
            case Cell.CELL_TYPE_NUMERIC:
                // 判断是否是日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date dateCellValue = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formatDate = sdf.format(dateCellValue);
                    System.out.print(formatDate + " | ");
                }else {
                    // 是数字的转换成字符串获取，防止数字过长
                    // 重新设置单元格的数据类型
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String s = cell.toString();
                    System.out.print(s + " | ");
                }
                break;
            // 匹配不能转换的错误
            case Cell.CELL_TYPE_ERROR:
                break;
            // 默认结果
            default:
                break;
        }
    }


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);

        // -----------

        log.info("LocalDate.now() - > {}",LocalDate.now());
        log.info("LocalDateTime.now() - > {}",LocalDateTime.now());
        String format1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        log.info("LocalDateTime.now().format - > {}",format1);

        // ------
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        log.info("localDateTime - > {}",localDateTime);
        log.info("date - > {}",date);

    }
}
