package com.dfp.alibabaprovider4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaProvider4Application {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaProvider4Application.class, args);
    }

}
