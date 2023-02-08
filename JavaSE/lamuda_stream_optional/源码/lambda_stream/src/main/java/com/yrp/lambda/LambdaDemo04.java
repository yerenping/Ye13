package com.yrp.lambda;

import java.util.function.Function;

/**
 * @author: YeRenping
 * @Date: 2022/12/13
 * @Description: com.yrp
 */
public class LambdaDemo04 {
    public static void main(String[] args) {
        Integer res = typeConver((String s)-> {
            return Integer.parseInt(s);
        });
        System.out.println(res);

        String s = typeConver((String ss)-> {
            return ss + "牛逼";
        });
        String s2 = typeConver(ss->
             ss + "牛逼"
        );
        System.out.println(s);
    }


    public static <R>R typeConver(Function<String,R> function) {
        String s = "123";
        R result = function.apply(s);
        return result;
    }

}
