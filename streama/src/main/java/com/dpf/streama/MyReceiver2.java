package com.dpf.streama;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author dpf
 * @create 2020-05-19 22:40
 * @email 446933040@qq.com
 * 自定义消息接收
 */
@EnableBinding(MyChannel.class)
public class MyReceiver2 {

  public final static Logger logger =  LoggerFactory.getLogger(MyReceiver2.class);

    @StreamListener(MyChannel.INPUT)
    public void receiver(Object playload){
        logger.info("receiver2"+playload);
    }
}
