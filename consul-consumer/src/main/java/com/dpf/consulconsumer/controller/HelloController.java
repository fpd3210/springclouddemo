package com.dpf.consulconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public void hello(){

        ServiceInstance choose = loadBalancerClient.choose("consul-provider");
        System.out.println(choose);
        System.out.println(choose.getUri());
        System.out.println(choose.getHost());
        String forObject = restTemplate.getForObject(choose.getUri() + "/hello", String.class);
        System.out.println(forObject);
    }

}
