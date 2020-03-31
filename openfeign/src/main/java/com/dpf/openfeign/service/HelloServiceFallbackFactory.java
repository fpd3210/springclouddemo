package com.dpf.openfeign.service;

import com.dpf.commmons.pojo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author dpf
 * @create 2020-03-31 22:41
 * @email 446933040@qq.com
 */
@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String hello() {
                return "error1----";
            }

            @Override
            public String hello2(String name) {
                return "error2----";
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
        };
    }
}
