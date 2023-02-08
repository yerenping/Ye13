package com.yrp.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: YeRenping
 * @Date: 2023/1/2
 * @Description:
 */
@Configuration
public class ConsumerConfig {

    @Bean
    @LoadBalanced // 家Ribbon自动添加进来了
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

