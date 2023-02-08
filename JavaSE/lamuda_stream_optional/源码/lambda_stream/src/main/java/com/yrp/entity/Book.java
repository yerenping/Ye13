package com.yrp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: YeRenping
 * @Date: 2022/12/13
 * @Description: com.yrp.entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book implements Comparable<Book> {
    // id
    private Long id;
    // 书名
    private String name;
    //分类
    private String category;
    //成绩
    public Integer score;

    public String intro;

    @Override
    public int compareTo(Book o) {
        return this.score - o.score;
    }
}
