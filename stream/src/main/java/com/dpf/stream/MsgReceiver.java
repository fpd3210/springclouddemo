package com.dpf.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;



/**
 * @author dpf
 * @create 2020-05-19 16:15
 * @email 446933040@qq.com
 */
// 表示绑定的消息通达，Sink为默认实现通道
@EnableBinding(Sink.class)
public class MsgReceiver {
    public static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(Sink.INPUT)
    public void reveicer(Object payload){
        logger.info("Receiver:"+payload);
    }
}
