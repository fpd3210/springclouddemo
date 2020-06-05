package com.dpf.alibabaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaProviderApplication.class, args);
    }

}
