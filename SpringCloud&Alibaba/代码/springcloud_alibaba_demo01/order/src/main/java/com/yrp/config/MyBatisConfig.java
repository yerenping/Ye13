package com.yrp.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 依靠数据源dataSource
        factoryBean.setDataSource(dataSource);
        // 扫描pojo包(实体类),自动取别名（小写）
        factoryBean.setTypeAliasesPackage("com.yrp.pojo");
        return factoryBean;
    }

    // 扫描dao层的接口（mapper）
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.yrp.dao");
        return msc;
    }
}