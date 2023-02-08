package com.yrp.read.test;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson2.JSON;
import com.yrp.read.entity.DemoData;

/**
 * @author: YeRenping
 * @Date: 2023/1/21
 * @Description:
 */
@Slf4j
public class SimpleReadTest {

    public static String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/easyexcel/easyexcel/";

    @Test
    public void t1(){
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = PATH +  "EasyExcel_叶仁平.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }

}
