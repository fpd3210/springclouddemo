package com.dpf.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author dpf
 * @create 2020-05-19 22:35
 * @email 446933040@qq.com
 * 自定义消息通道
 *
 */
public interface MyChannel {

    String INPUT = "pikachues-input";
    String output = "pikachues-output";

    @Output(output)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();

    /*
    注意：
    1.两个消息通道的名字是不一样的
    2.从F版开始，默认使用通道名称作为实例命令，所以这里通道名字不可以相同(早起版本可以相同)
        这样的话，为了能够正常收发消息，需要闷闷在application.properties中做一些额外配置
     */
}
