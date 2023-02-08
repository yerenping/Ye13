package com.yrp.entity;

import java.util.List;

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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author implements Comparable<Author> {
    // id
    private Long id;
    //姓名
    private String name;

    // 年龄
    private Integer age;

    //简介
    private String intro;
    //作品
    private List<Book> books;

    @Override
    public int compareTo(Author author) {
        return this.getAge() - author.getAge();
    }
}
