package com.yrp.lambda;

import java.util.function.IntPredicate;

/**
 * @author: YeRenping
 * @Date: 2022/12/13
 * @Description: com.yrp
 */
public class LambdaDemo03 {
    public static void main(String[] args) {
        printNum((int value) -> {
            return value % 2 == 0;
        });
    }
    public static void printNum(IntPredicate predicate) {
        int[] arr = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }
}
