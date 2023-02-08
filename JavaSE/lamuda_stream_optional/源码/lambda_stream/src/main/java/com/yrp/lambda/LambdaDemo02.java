package com.yrp.lambda;

import java.util.function.IntBinaryOperator;

/**
 * @author: YeRenping
 * @Date: 2022/12/13
 * @Description: com.yrp
 */
public class LambdaDemo02 {
    public static void main(String[] args) {
        int i = calculateNum((left, right) -> {
            return left + right;
        });
        System.out.println(i);
    }

    public static int calculateNum(IntBinaryOperator op){
        int a =  10;
        int b =  20;
        return op.applyAsInt(20, 20);
    }
}
