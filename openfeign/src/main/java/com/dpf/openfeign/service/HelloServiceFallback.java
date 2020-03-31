package com.dpf.openfeign.service;

import com.dpf.commmons.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dpf
 * @create 2020-03-31 22:39
 * @email 446933040@qq.com
 */
@Component
@RequestMapping("pikachues") //防止请求地址重复
public class HelloServiceFallback implements HelloService{

    @Override
    public String hello() {
        return "error1";
    }

    @Override
    public String hello2(String name) {
        return "error2";
    }

    @Override
    public User add2(User user) {
        return null;
    }

    @Override
    public void deleteUser2(Integer id) {

    }

    @Override
    public void getUserByName(String name) {

    }
}
