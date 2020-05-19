package com.dpf.configclient_bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ConfigclientBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigclientBusApplication.class, args);
    }

}
