package com.yrp.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 部门表
 * @TableName dept
 */
@Data
public class Dept implements Serializable {
    /**
     * 部门编号
     */
    private Long deptno;

    /**
     * 部门名字
     */
    private String dname;

    /**
     * 数据库来源
     */
    private String dbSource;

    private static final long serialVersionUID = 1L;
}