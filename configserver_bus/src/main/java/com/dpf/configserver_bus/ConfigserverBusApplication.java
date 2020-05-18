package com.dpf.configserver_bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigserverBusApplication.class, args);
    }

}
