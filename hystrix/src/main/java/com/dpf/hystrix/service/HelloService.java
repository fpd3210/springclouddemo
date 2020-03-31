package com.dpf.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author dpf
 * @create 2020-03-31 12:38
 * @email 446933040@qq.com
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 发起远程调用，去调用eureka-provider中提供的/hello接口
     * 但是这个调用可能会失败
     *
     * 处理失败：方法加上@HystrixCommand注解，配置fallbackMethod属性
     *          这个属性表示调用失败时临时替代的方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "error",ignoreExceptions = ArithmeticException.class)
    public String hello(){
//        int i = 10 / 0;
        return restTemplate.getForObject("http://eureka-provider/hello",String.class);
    }
    /**
     * 服务降级
     * 注意这个方法名要和fallbackMethod一致
     * 方法返回值也要和对应的方法一致
     *
     * error中也可以用@HystrixCommand调用其他接口
     * @return
     */
    public String error(Throwable t){
        return "error"+t.getMessage();
    }

    /**
     * 注解实现请求异步调用
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    public Future<String> hello2(){
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://eureka-provider/hello",String.class);
            }
        };
    }

    /**
     * 测试缓存
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "error2")
    @CacheResult //表示该方法的结果会被缓存起来，缓存的默认key为方法名，value为方法的返回值
    public String hello3(String name){
        return restTemplate.getForObject("http://eureka-provider/hello2?name={1}",String.class,name);
    }

    public String error2(String name){
        return "error2";
    }
}
