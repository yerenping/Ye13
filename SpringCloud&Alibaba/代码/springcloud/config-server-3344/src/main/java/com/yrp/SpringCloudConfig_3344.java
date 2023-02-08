package com.yrp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: YeRenping
 * @Date: 2023/1/20
 * @Description:
 */
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfig_3344 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfig_3344.class, args);
    }

}
