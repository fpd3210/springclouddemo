package com.dpf.streamprovider.controller;

import com.dpf.streamprovider.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dpf
 * @create 2020-05-20 20:29
 * @email 446933040@qq.com
 */
@RestController
public class SendMessageController {

    @Autowired
    MessageProvider messageProvider;

    @GetMapping("/hello")
    public void send(){
        messageProvider.send();
    }
}
