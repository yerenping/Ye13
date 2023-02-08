package com.yrp.write.entity;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

import com.alibaba.excel.annotation.ExcelProperty;

@Data
@Accessors(chain = true)
public class IndexData {
    @ExcelProperty(value = "字符串标题", index = 0)
    private String string;
    @ExcelProperty(value = "日期标题", index = 1)
    private Date date;
    /**
     * 这里设置3 会导致第二列空的
     */
    @ExcelProperty(value = "数字标题", index = 3)
    private Double doubleData;
}