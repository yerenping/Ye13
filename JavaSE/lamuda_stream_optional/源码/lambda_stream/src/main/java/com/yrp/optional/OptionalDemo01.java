package com.yrp.optional;

import java.util.Optional;

import com.yrp.entity.Author;

/**
 * @author: YeRenping
 * @Date: 2022/12/19
 * @Description: com.yrp.optional
 */
public class OptionalDemo01 {


    public static void main(String[] args) {
        Optional<Author> authorOptional = getAutor();
        Author author = authorOptional.orElseGet(() -> new Author(1L,"叶仁平", 18, "牛逼",null));
        System.out.println(author);
    }

    public static Optional<Author> getAutor(){
        Author author = new Author(1L,"贺晶晶",33,"牛逼的人",null);
        return Optional.ofNullable(null);
    }

}
