package com.yrp.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: YeRenping
 * @Date: 2023/1/23
 * @Description:
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;

}
