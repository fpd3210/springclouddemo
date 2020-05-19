package com.example.eurekaserver_bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBusApplication.class, args);
    }

}
