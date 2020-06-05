package com.dpf.alibabaconsumer1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dpf
 * @create 2020-06-04 9:16 下午
 * @email 446933040
 */
@RestController
public class OrderNacosController {
    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/consumer/hello")
    public String hello(){
        return restTemplate.getForObject(serverUrl+"/provider/hello",String.class);
    }

}
