package com.yrp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yrp.entity.Dept;

/**
 * @author: YeRenping
 * @Date: 2023/1/14
 * @Description:
 */
@RestController
public class DeptConsumerController {
    /**
     * url , 实体：Map，Class<T>, responseType
     */
    private final RestTemplate restTemplate;
    private static  final String REST_URL_PREFIX =  "http://localhost:8001";

    @Autowired
    public DeptConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/consumer/dept/add")
    public boolean add(@RequestBody Dept dept){
        System.out.println(dept);
        return restTemplate.postForObject(REST_URL_PREFIX+ "/dept/add", dept, Boolean.class);
    }


    @GetMapping("/consumer/dept/list")
    public List<Dept> list(Dept dept){
        return restTemplate.getForObject(REST_URL_PREFIX+ "/dept/list",List.class);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept getById(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/"+id, Dept.class);
    }



}
