package com.yrp.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: YeRenping
 * @Date: 2023/1/8
 * @Description:
 */
public class ApiController {
    @GetMapping("/api1/demo1")
    public String demo1(){
        return "demo";
    }

    @GetMapping("/api1/demo2")
    public String demo2(){
        return "demo";
    }

    @GetMapping("/api2/demo1")
    public String demo3(){
        return "demo";
    }

    @GetMapping("/api2/demo2")
    public String demo4(){
        return "demo";
    }
}
