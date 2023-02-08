package com.yrp.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yrp.producer.service.ProducerService;

/**
 * @author: YeRenping
 * @Date: 2023/1/2
 * @Description:
 */
@RestController
public class ProducerController {

    private final ProducerService producerService;

    private final String port;

    @Autowired
    public ProducerController(ProducerService producerService, @Value("${server.port}") String port) {
        this.producerService = producerService;
        this.port = port;
    }

    @GetMapping("/index")
    public String index(){
        return this.port;
    }
    @GetMapping("/list")
    public String list(){
        return "list";
    }

    @GetMapping("/test1")
    public String test1(){
        producerService.test();
        return "test1";
    }

    @GetMapping("/test2")
    public String test2(){
        producerService.test();
        return "test2";
    }


}
