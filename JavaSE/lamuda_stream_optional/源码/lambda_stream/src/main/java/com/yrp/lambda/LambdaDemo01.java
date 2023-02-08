package com.yrp.lambda;

/**
 * @author: YeRenping
 * @Date: 2022/12/13
 * @Description: com.yrp
 */
public class LambdaDemo01 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程中的run方法被执行了");
            }
        }).start();

        // lambda 表达式
        new Thread(()->{
            System.out.println("新线程中的run方法被执行了");
        }).start();
    }

}
