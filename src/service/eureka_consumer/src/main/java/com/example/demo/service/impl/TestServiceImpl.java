package com.example.demo.service.impl;

import com.example.demo.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    @Override
    public String hello() {
        restTemplate.getForObject("http://eureka-service/eureka-service/hello", String.class);

        return "hello, eureka-consumer";
    }

    @Override
    public String error() {
        return "eureka-service 出现异常";
    }
}
