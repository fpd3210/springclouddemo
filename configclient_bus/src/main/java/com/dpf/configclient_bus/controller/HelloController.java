package com.dpf.configclient_bus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //动态刷新
public class HelloController {

    @Value("${pikachues}")
    String pikachues;

    @GetMapping("/hello")
    public String hello(){
        return this.pikachues;
    }
}
