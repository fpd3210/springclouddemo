package com.dpf.streamprovider.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author dpf
 * @create 2020-05-20 20:27
 * @email 446933040@qq.com
 */
@EnableBinding(Source.class)
public class MessageProvider {

    @Resource
    MessageChannel output;

    public void send(){
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serial = " + serial);
    }
}
