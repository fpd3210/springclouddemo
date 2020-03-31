package com.dpf.openfeign.controller;

import com.dpf.commmons.pojo.User;
import com.dpf.openfeign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author dpf
 * @create 2020-03-31 20:54
 * @email 446933040@qq.com
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() throws UnsupportedEncodingException {
        String pikachues = helloService.hello2("pikachues");
        System.out.println(pikachues);

        User user = new User();
        user.setUsername("pikachues");
        user.setPassword("123");
        user.setId(99);
        User u = helloService.add2(user);
        System.out.println(u);
        helloService.deleteUser2(99);
        helloService.getUserByName(URLEncoder.encode("小明","UTF-8"));

        return helloService.hello();
    }
}
