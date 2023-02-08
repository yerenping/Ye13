package com.yrp.main;

import java.util.Arrays;

import com.yrp.entity.Product;

/**
 * @author: YeRenping
 * @Date: 2023/1/29
 * @Description:
 */
public class T {
    public static void main(String[] args) {

        Product one = new Product().setId(1).setTitile("牛逼1`");
        Product two = new Product().setId(2).setTitile("牛逼2");
        Product three = new Product().setId(3).setTitile("牛逼3");
        Product four = new Product().setId(4).setTitile("牛逼4");
        Product[] p = {one,two,three,four};

        Integer[] integers = Arrays.stream(p).map(product -> product.getId())
                                        .toArray(value -> new Integer[value]);
        System.out.println(Arrays.toString(integers));


    }
}
