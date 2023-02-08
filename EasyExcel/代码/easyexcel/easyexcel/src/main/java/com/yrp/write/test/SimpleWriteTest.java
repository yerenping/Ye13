package com.yrp.write.test;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import com.yrp.write.entity.DemoData;

/**
 * @author: YeRenping
 * @Date: 2023/1/21
 * @Description:
 */
@Slf4j
public class SimpleWriteTest {
    public static String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/easyexcel/easyexcel/";


    // 数据生成
    private List<DemoData> data() {
        List<DemoData> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData()
                    .setString("字符串" + i)
                    .setDate(new Date())
                    .setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


    /**
     * lamuda表达式写法
     */
    @Test
    public void simpleWrite() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName =  PATH + "EasyExcel_叶仁平.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class)
                .sheet("是叶十三")
                .doWrite(() -> data());
        log.info("写入完毕，文件已生成！");
    }

    /**
     * 直接传入“List集合”写法
     */
    @Test
    public void simpleWrite2() {
        // 写法2
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        String fileName =  PATH + "EasyExcel_叶仁平2.xlsx";
        EasyExcel.write(fileName, DemoData.class).
                sheet("模板").
                doWrite(data());
        log.info("写入完毕，文件已生成！");
    }

    /**
     * build()方法
     */
    @Test
    public void simpleWrite3() {
        // 写法3
        // 这里 需要指定写用哪个class去写
        String fileName =  PATH + "EasyExcel_叶仁平2.xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        }
    }
}
