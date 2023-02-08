package com.yrp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yrp.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/save")
    public String save(){
        String username = "张三";
        //订单
        this.orderService.save(username);
//        int i = 10/0;
        //支付
        this.restTemplate.getForObject("http://pay/save",String.class);
        return "success";
    }
}