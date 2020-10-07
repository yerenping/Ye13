package com.ye.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component//一定不要忘记把处理器组件添加到IOC容器中！（Component）
@Slf4j
public class MyDataObjectHandler implements MetaObjectHandler{
    //插入时候的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入执行.......");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    //更新时候的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新执行.......");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}

