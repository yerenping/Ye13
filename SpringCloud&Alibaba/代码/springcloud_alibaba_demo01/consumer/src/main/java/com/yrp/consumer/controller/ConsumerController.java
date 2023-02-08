package com.yrp.consumer.controller;

import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: YeRenping
 * @Date: 2023/1/2
 * @Description:
 */
@Slf4j
@RestController
public class ConsumerController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;


    @Autowired
    public ConsumerController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/instances")
    public List<ServiceInstance> getInstances(){
        List<ServiceInstance> list = discoveryClient.getInstances("producer");
        return list;
    }

    @GetMapping("/index")
    public String index(){
       // 1、找到producer的实例
        List<ServiceInstance> list = discoveryClient.getInstances("producer");
        // 2、完整调用路径
        int r = new Random().nextInt(3);
        ServiceInstance serviceInstance = list.get(r);
        String url = serviceInstance.getUri() + "/index";
        // 3、调用
        log.info("调用了端口为:{}" ,serviceInstance.getPort());
        return  "调用了端口为" +serviceInstance.getPort()+"的服务，返回结果为："+ restTemplate.getForObject(url, String.class);

    }
    @GetMapping("/index2")
    public String index2(){
        return  this.restTemplate.getForObject("http://producer/index", String.class);
    }





}
