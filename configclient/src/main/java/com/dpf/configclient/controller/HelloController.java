package com.dpf.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dpf
 * @create 2020-04-06 17:05
 * @email 446933040@qq.com
 */
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
