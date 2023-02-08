package com.yrp.write.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import com.yrp.write.entity.DemoData;
import com.yrp.write.entity.IndexData;

/**
 * @author: YeRenping
 * @Date: 2023/1/21
 * @Description:
 */
@Slf4j
public class excludeOrIncludeWriteTest {

    public static String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/easyexcel/easyexcel/";

    @Test
    public void t1(){
        String fileName =PATH + "排除或包含指定字段.xlsx";
        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeSet = new HashSet<>();
        excludeSet.add("date");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).excludeColumnFiledNames(excludeSet).sheet("排除date")
                .doWrite(data());
    }


    /**
     * 数据生成，生成10行数据
     * @return
     */
    private List<IndexData> data() {
        List list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            IndexData indexData = new IndexData().setString("字符串" + i)
                    .setDate(new Date())
                    .setDoubleData(0.56);
            list.add(indexData);
        }
        return list;
    }


}
