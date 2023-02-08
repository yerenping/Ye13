package com.yrp.guava;

import com.yrp.pojo.Car;

/**
 * @author: YeRenping
 * @Date: 2023/1/13
 * @Description:
 */
public class ToStringHelperDemo {
    public static void main(String[] args) {

        Car car1 = new Car(1, "Audi", 52642);
        Car car2 = new Car(2, "Mercedes", 57127);
        Car car3 = new Car(3, "Skoda", 9000);

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3);
    }
}
