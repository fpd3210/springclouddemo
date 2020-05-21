package com.dpf.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dpf
 * @create 2020-05-19 22:44
 * @email 446933040@qq.com
 * 自定义消息发送
 */
@RestController
public class SendMessageController {
    @Autowired
    MyChannel myChannel;

    @GetMapping("/hello")
    public void hello(){
        myChannel.output().send(MessageBuilder.withPayload("hello springcloud stream").build());
    }
}
