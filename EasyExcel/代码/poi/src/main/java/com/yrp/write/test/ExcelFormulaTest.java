package com.yrp.write.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author: YeRenping
 * @Date: 2023/1/21
 * @Description:
 */
@Slf4j
public class ExcelFormulaTest {

    private static final String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/poi/";
    private FileInputStream     fis  = null;

    @Test
    public void testExcelFormula() {

        try {
            fis = new FileInputStream(new File(PATH + "公式.xlsx"));
            // 1、从磁盘中获取一个Excel工作簿
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            // 2、获取该工作簿中的工作表
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet != null) {
                String sheetName = sheet.getSheetName();
                log.info("工作表的名字是-{}", sheetName);
                // 获取坐标为[4,0]的单元格
                Row row4 = sheet.getRow(4);
                Cell cell41 = row4.getCell(0);
                // 获取计算公式
                XSSFFormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator(workbook);
                // 输出单元格内容
                int cellType = cell41.getCellType();
                switch (cellType){
                    // 公式
                    case Cell.CELL_TYPE_FORMULA:
                        // 获取公式
                        String formule = cell41.getCellFormula();
                        //  打印公式
                        System.out.println(formule);
                        // 打印值
                        System.out.println(cell41.getNumericCellValue());
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            log.warn(e.getMessage(),e);
            log.debug(e.getMessage(),e);
            log.error(e.getMessage(),e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("{}","读取完毕");
    }

}
