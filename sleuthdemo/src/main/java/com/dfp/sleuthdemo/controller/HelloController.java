package com.dfp.sleuthdemo.controller;

import com.dfp.sleuthdemo.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dpf
 * @create 2020-05-21 15:57
 * @email 446933040@qq.com
 */
@RestController
public class HelloController {

   public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

   @Autowired
   RestTemplate restTemplate;

   @Autowired
   HelloService helloService;

   @GetMapping("/hello")
    public void hello1(){
        logger.info("hello spring cloud sleuth");
    }

    @GetMapping("/hello2")
    public String hello2() throws InterruptedException {
        logger.info("hello2");
        Thread.sleep(500);
        return restTemplate.getForObject("http://localhost:8080/hello3",String.class);
    }
    @GetMapping("/hello3")
    public String hello3() throws InterruptedException {
        logger.info("hello3");
        Thread.sleep(500);
        return "hello3";
    }

    @GetMapping("/hello4")
    public String hello4(){
       logger.info("hello4");
       return helloService.backgroundFun();
    }
}
