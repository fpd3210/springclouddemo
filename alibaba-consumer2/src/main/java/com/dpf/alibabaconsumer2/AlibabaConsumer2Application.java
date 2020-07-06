package com.dpf.alibabaconsumer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AlibabaConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaConsumer2Application.class, args);
    }

}
