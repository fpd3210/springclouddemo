package com.dpf.alibabaprovider2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dpf
 * @create 2020-06-04 9:20 下午
 * @email 446933040
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    String serverPort;

    @GetMapping("/provider/hello")
    public String hello(){
        return "nacos1 hello"+serverPort;
    }
}
