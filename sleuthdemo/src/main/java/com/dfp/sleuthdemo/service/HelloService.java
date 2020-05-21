package com.dfp.sleuthdemo.service;

import com.dfp.sleuthdemo.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author dpf
 * @create 2020-05-21 17:19
 * @email 446933040@qq.com
 */
@Service
public class HelloService {

    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Async
    public String backgroundFun(){
        logger.info("backgroundFun");
        return "backgroundFun";
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void sche1(){
        logger.info("start:");
        backgroundFun();
        logger.info("end:");
    }
}
