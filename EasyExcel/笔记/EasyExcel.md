# Apache Poi

## 1、来历

官方网站：https://poi.apache.org/



## 2、Excel的组成

- 工作簿(workbook)
  - 工作表(sheet)
    - 行(row)
      - (单元格cell)

## 3、POI -写实战

> 创建一个03版本的excel表格(.xls)

（1）创建一个maven项目，引入如下依赖

```xml
<dependencies>
        <!--03版本xls-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>3.9</version>
        </dependency>

        <!--07版本xlsx-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>3.9</version>
        </dependency>
  			 <!--lombok + sl4j -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.25</version>
        </dependency>
  			 <!--junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>
```

（2）编写测试类，分别生成03版本，07版本

> 文件后缀 xls xlsx
>
> HSSFWorkbook 03版本
>
> XSSFWorkbook 07版本

```java
package com.yrp.test;

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
```

## 4、POI 数据批量导入

###  03批量版本

> **大文件写 HSFF** 
>
> 优点：过程中讲数据写入缓存，不操作磁盘，最后一次写入磁盘，速度快
>
> 缺点：当插入数据行数超过65536行，则会发生异常

```java
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
```

![image-20230121115157056](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230121115157.png)





### 07版 批量版本

> 大文件写入XSSF
>
> 缺点：写入时速度非常慢，非常消耗内存，也会发生内存溢出，如100万条
>
> 优点：可以写入较大数据，如20w条

```java
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

```

![image-20230121115755137](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230121115755.png)

> 大文件写入SXSSF
>
> 优点：可以写入非常大量的数据，如100w条，写入数据快，占用更少的内存	

注意：

​	1、过程中会产生临时文件，需要及时清理文件

​	2、默认有100条记录保存在内存，如果超过这个数量，则最前的数据将被写入临时文件中

​	3、如果想要自定义内存中的数量，可以使用new SXSSFWork(数量)

```java
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

```



## 5、POI-读实战

> 03版本读取Excel
>
> 注意：最麻烦的点-读取不同类型的Excel表格中的值

```java
private static final String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/poi/";
private FileInputStream     fis  = null;
private FileOutputStream    fos  = null;

/**
 * 读取一个03版本的excel中的内容
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
```



> 07版本读取Excel



```java
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
```



### 6、POI读取不同类型的数据

```java
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
```



![image-20230121133121677](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230121133122.png)



# EasyExcel

> 官方网站：https://easyexcel.opensource.alibaba.com（优质文档）

Java解析、生成Excel比较有名的框架有Apache poi、jxl。但他们都存在一个严重的问题就是非常的耗内存，poi有一套SAX模式的API可以一定程度的解决一些内存溢出的问题，但POI还是有一些缺陷，比如07版Excel解压缩以及解压后存储都是在内存中完成的，内存消耗依然很大。
easyexcel重写了poi对07版Excel的解析，一个3M的excel用POI sax解析依然需要100M左右内存，改用easyexcel可以降低到几M，并且再大的excel也不会出现内存溢出；03版依赖POI的sax模式，在上层做了模型转换的封装，让使用者更加简单方便



## 1、写入数据

### （1）简单写入数据



1、导入依赖

```xml
 <dependencies>
        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
        </dependency>
        <!--日期格式化工具类-->
        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>2.10.1</version>
        </dependency>
        <!--单元测试-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <!--easyexcel-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <version>3.1.1</version>
        </dependency>
        <!--guava-->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>19.0</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.25</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
```



2、编写实体类

```java
package com.yrp.entity;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

@Data
@Accessors(chain = true) // 开启链式编程
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
```



3、编写测试类`SimpleWriteTest`

> 三种简单的写入方式

```java

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
        EasyExcel.write(fileName, DemoData.class) // 文件名 ， 格式类
                .sheet("是叶十三") // 工作表
                .doWrite(() -> data()); // data() 写入的数据集合
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
        log.info("写入完毕，文件已生成！");
    }
}
```



4、观察结果如下

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230121161500.png" alt="image-20230121161459748" style="zoom:50%;" /> 





### （2）根据参数只到处指定列

1、编写实体类

```java
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
```



2、编写测试类

```java
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
```

3、测试生成文件后，观察结果

> 结果发现date列已经被排除了



![image-20230121161745340](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230121161745.png)





## 2、读取数据

> 参考：https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read

1、编写数据类型类

```java
@Getter
@Setter
@EqualsAndHashCode
/**
 * @Data 注解包含如下
 * 所有属性的get和set方法
 * toString 方法
 * hashCode方法
 * equals方法
  */
public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;
}
```



2、编写数据访问层对象，将Excel中读取到的数据持久化到数据库

```java
@Getter
@Setter
@EqualsAndHashCode
/**
 * @Data 注解包含如下
 * 所有属性的get和set方法
 * toString 方法
 * hashCode方法
 * equals方法
  */
public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;
}
```

3、编写监听器

> 注意：应该提前到fastjson

```java
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class DemoDataListener implements ReadListener<DemoData> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    // 注入DAO层
    private DemoDAO demoDAO;

    public DemoDataListener() {
        demoDAO = new DemoDAO();
    }

    public DemoDataListener(DemoDAO demoDAO) {
        this.demoDAO = demoDAO;
    }

    /**
     * 读取数据的时候，会执行invoke 方法
     * 解决OOM问题
     * @param data 类型
     * @param context AnalysisContext 分析上下文
     */
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData(); // 持久化
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        demoDAO.save(cachedDataList);
        log.info("存储数据库成功！");
    }
}
```

4、测试读取`EasyExcel_叶仁平.xlsx`这个文件，编写测试类

```java
@Slf4j
public class SimpleReadTest {

    public static String PATH = "/Users/ouyangyansong/Documents/Java开发/projects/easyexcel/easyexcel/";


    @Test
    public void t1(){
        String fileName = PATH +  "EasyExcel_叶仁平.xlsx";

        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }

}
```

5、观察读取结果

![image-20230121225230323](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230121225230.png)





