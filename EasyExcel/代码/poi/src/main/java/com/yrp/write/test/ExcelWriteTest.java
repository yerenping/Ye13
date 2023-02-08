package com.yrp.write.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
public class ExcelWriteTest {
    private static final String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/poi/";
    private FileOutputStream    fos  = null;
    /**
     * 创建一个03版本的excel
     */
    @Test
    public void testWriteExcel03Version() {
        // 1、创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet("Java后端");
        // ********第1行的第1个单元格**********
        // 3、创建行
        Row row = sheet.createRow(0);
        // 4、创建一个单元格
        Cell cell = row.createCell(0);
        cell.setCellValue("今日新增关注");
        // ********第2行的第1个单元格**********
        // 3、创建行
        Row row2 = sheet.createRow(1);
        // 4、创建一个单元格
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue("今日新增点赞");
        // ********第3行的第1个单元格**********
        // 3、创建行
        Row row3 = sheet.createRow(2);
        // 4、创建一个单元格
        Cell cell3 = row3.createCell(0);
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        cell3.setCellValue(dateTime);
        // ******生成一张表*****
        try {
            fos = new FileOutputStream(PATH + "是叶十三【03版本】.xls");
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        log.info("是叶十三【03版本】.xls 生成完毕!");
    }


    /**
     * 创建一个07版本的excel
     */
    @Test
    public void testWriteExcel07Version() {
        // 1、创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet("数据库原理");
        // ********第1行的第1个单元格**********
        // 3、创建行
        Row row = sheet.createRow(0);
        // 4、创建一个单元格
        Cell cell = row.createCell(0);
        cell.setCellValue("今日新增关注");
        // ********第2行的第1个单元格**********
        // 3、创建行
        Row row2 = sheet.createRow(1);
        // 4、创建一个单元格
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue("今日新增点赞");
        // ********第3行的第1个单元格**********
        // 3、创建行
        Row row3 = sheet.createRow(2);
        // 4、创建一个单元格
        Cell cell3 = row3.createCell(0);
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        cell3.setCellValue(dateTime);
        // ******生成一张表*****
        try {
            fos = new FileOutputStream(PATH + "是叶十三【07版本】.xlsx");
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        log.info("是叶十三【07版本】.xlsx 生成完毕!");
    }


}
