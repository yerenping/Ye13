package com.ye.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*扫描mapper文件夹*/
@MapperScan("com.ye.mapper")

@EnableTransactionManagement //事务管理

@Configuration //配置类
public class MybatisPlusConfig {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return new PaginationInterceptor();
    }

    //逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }


    //sql执行效率插件
    @Bean
    @Profile({"dev","test"}) //设置dev环境和test环境开启
    public  PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor pi = new PerformanceInterceptor();
        pi.setMaxTime(100); //设置sql能够执行最大时间，如果超过了则不执行
        pi.setFormat(true);
        return new PerformanceInterceptor();
    }


}
