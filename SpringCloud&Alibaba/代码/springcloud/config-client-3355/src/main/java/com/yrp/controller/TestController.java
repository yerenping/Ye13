package com.yrp.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YeRenping
 * @Date: 2023/1/20
 * @Description:
 */
@RestController
@Slf4j
public class TestController {
    @Value("${spring.application.name}")
    private  String applicationName;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public void t(){
        System.out.println(applicationName);
        System.out.println(port);
        log.debug("applicationName={}", applicationName);
        log.debug("port={}", port);
    }

}
