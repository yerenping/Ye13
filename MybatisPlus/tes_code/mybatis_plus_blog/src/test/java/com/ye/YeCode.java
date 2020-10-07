package com.ye;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

// 代码自动生成器
public class YeCode{
    public static void main(String[] args) {
        // 构建一个代码自动生成器对象
        AutoGenerator mg =  new AutoGenerator();

        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("是叶十三");
        gc.setOpen(false);//是否打开文件
        gc.setFileOverride(false);// 是否覆盖
        gc.setServiceName("%sService");//去Service的I前缀
        gc.setIdType(IdType.ID_WORKER); //id全局唯一
        gc.setDateType(DateType.ONLY_DATE);// 日期类型
        gc.setSwagger2(true);

        mg.setGlobalConfig(gc);//将配置丢到自动生成器里面

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.104.231.144:3306/mybatis_plus_code?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mg.setDataSource(dsc);


        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("com.ye");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("servie");
        pc.setController("controller");
        mg.setPackageInfo(pc);


        //3、策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("t_blog","t_blog_tags","t_comment","t_tag","t_tag_copy");
        sc.setNaming(NamingStrategy.underline_to_camel); //下滑线转驼峰命名
        sc.setColumnNaming(NamingStrategy.underline_to_camel);//列-下滑线转驼峰命名
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        sc.setEntityLombokModel(true); //自动生成lombok
        sc.setLogicDeleteFieldName("deleted"); //逻辑删除配置
        sc.setRestControllerStyle(true);

        // 自动填充配置
        // 自动填充配置
        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(create_time);
        tableFills.add(update_time);
        sc.setTableFillList(tableFills);


        // 乐观锁配置：
        sc.setVersionFieldName("version");


        //开启restfull的驼峰命名
        sc.setRestControllerStyle(true);

        // url地址变为下划线
        /**
         *
         * localhost:8080/hello_id_23
         */
        sc.setControllerMappingHyphenStyle(true);
        mg.setStrategy(sc);

        //执行
        mg.execute();

    }
}