package com.dpf.eurekaprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dpf
 * @create 2020-03-30 15:07
 * @email 446933040@qq.com
 */
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello(){
        return "hello"+port;
    }
}
