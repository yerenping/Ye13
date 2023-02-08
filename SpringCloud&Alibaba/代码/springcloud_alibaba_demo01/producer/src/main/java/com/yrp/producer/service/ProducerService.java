package com.yrp.producer.service;

import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

/**
 * @author: YeRenping
 * @Date: 2023/1/7
 * @Description:
 */
@Service
public class ProducerService {


    @SentinelResource("test")
    public void test(){
        System.out.println("test");
    }

}
