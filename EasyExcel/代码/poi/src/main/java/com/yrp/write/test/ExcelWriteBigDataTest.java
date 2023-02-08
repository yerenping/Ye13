package com.yrp.write.test;

import java.io.FileOutputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;


/**
 * @author: YeRenping
 * @Date: 2023/1/20
 * @Description:
 */
@Slf4j
public class ExcelWriteBigDataTest {
    private static final String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/poi/";
    private FileOutputStream    fos  = null;

    @Test
    public void testWriteExcelBigData03Version() {
        long start = System.currentTimeMillis();
        // 1、创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet("牛客网");
        // 创建65535行
        for(int rowNumber = 0; rowNumber < 65537; rowNumber++){
            Row row = sheet.createRow(rowNumber);
            for (int cellNumber = 0; cellNumber < 10  ; cellNumber++) {
                Cell cell = row.createCell(cellNumber);
                cell.setCellValue(cellNumber);
            }
        }
        // ******生成一张表*****
        try {
            fos = new FileOutputStream(PATH + "是叶十三BigData【03版本】.xls");
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
        long end = System.currentTimeMillis();
        long time = (end - start) / 1000L;
        log.info("本次写入耗时：{}",time);
        log.info("是叶十三【03版本】.xls 生成完毕!");
    }

    /**
     * 插入65537行数据，消耗的时间比较长
     */
    @Test
    public void testWriteExcelBigData07Version() {
        long start = System.currentTimeMillis();
        // 1、创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet("牛客网");
        // 创建65535行
        for(int rowNumber = 0; rowNumber < 65537; rowNumber++){
            Row row = sheet.createRow(rowNumber);
            for (int cellNumber = 0; cellNumber < 10  ; cellNumber++) {
                Cell cell = row.createCell(cellNumber);
                cell.setCellValue(cellNumber);
            }
        }
        // ******生成一张表*****
        try {
            fos = new FileOutputStream(PATH + "是叶十三BigData【07版本】.xlsx");
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
        long end = System.currentTimeMillis();
        long time = (end - start) / 1000L;
        log.info("本次写入耗时：{}",time);
        log.info("是叶十三【07版本】.xls 生成完毕!");
    }



    /**
     * 插入65537行数据，消耗的时间比较长
     */
    @Test
    public void testWriteExcelBigData07VersionEnhance() {
        long start = System.currentTimeMillis();
        // 1、创建一个工作簿
        SXSSFWorkbook sxssfWork = new SXSSFWorkbook();
        // 2、创建一个工作表
        Sheet sheet = sxssfWork.createSheet("叶仁平");
        // 创建65535行
        for(int rowNumber = 0; rowNumber < 100000; rowNumber++){
            Row row = sheet.createRow(rowNumber);
            for (int cellNumber = 0; cellNumber < 10  ; cellNumber++) {
                Cell cell = row.createCell(cellNumber);
                cell.setCellValue(cellNumber);
            }
        }
        // ******生成一张表*****
        try {
            fos = new FileOutputStream(PATH + "是叶十三BigData_sxssf【07版本】.xlsx");
            sxssfWork.write(fos);
            sxssfWork.dispose();
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
        long end = System.currentTimeMillis();
        long time = (end - start) / 1000L;
        log.info("本次写入耗时：{}",time);
        log.info("是叶十三【07版本】_sxsff.xls 生成完毕!");
    }



}
