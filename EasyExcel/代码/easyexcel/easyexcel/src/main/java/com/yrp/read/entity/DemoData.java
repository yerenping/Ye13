package com.yrp.read.entity;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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