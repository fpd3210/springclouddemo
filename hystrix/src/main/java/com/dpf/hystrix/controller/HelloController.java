package com.dpf.hystrix.controller;

import com.dpf.commmons.pojo.User;
import com.dpf.hystrix.config.HelloCommand;
import com.dpf.hystrix.config.UserCollapseCommand;
import com.dpf.hystrix.service.HelloService;
import com.dpf.hystrix.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author dpf
 * @create 2020-03-31 12:38
 * @email 446933040@qq.com
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    /**
     * 注解实现调用
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        System.out.println(helloService.hello());
       return helloService.hello();
    }

    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过继承实现调用
     */
    @GetMapping("/hello2")
    public void hello2(){
        //直接执行
        HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("runn")), restTemplate);
        String execute = helloCommand.execute();
        System.out.println(execute);

        try {
            //一个实例只能执行一次所以在new一个，先入队再执行
            HelloCommand helloCommand2 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("runn")), restTemplate);
            Future<String> queue = helloCommand2.queue();
            String s = queue.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注解实现请求异步调用
     */
    @GetMapping("/hello3")
    public void hello3(){
        Future<String> hello2 = helloService.hello2();
        try {
            String s = hello2.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓存测试调用
     */
    @GetMapping("/hello4")
    public void hello4(){
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        String s = helloService.hello3("pikachues");
        System.out.println(s);
        s =helloService.hello3("pikachues");
        System.out.println(s);
        hystrixRequestContext.close();
    }


    @Autowired
    UserService userService;

    @GetMapping("/hello5")
    public void hello5() throws ExecutionException, InterruptedException {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
        UserCollapseCommand cmd1 = new UserCollapseCommand(userService, 99);
        UserCollapseCommand cmd2 = new UserCollapseCommand(userService, 98);
        UserCollapseCommand cmd3 = new UserCollapseCommand(userService, 97);
        Future<User> q1 = cmd1.queue();
        Future<User> q2 = cmd2.queue();
        Future<User> q3 = cmd3.queue();
        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        Thread.sleep(2000);
        UserCollapseCommand cmd4 = new UserCollapseCommand(userService, 96);
        Future<User> q4 = cmd4.queue();
        User u4 = q4.get();
        System.out.println(u4);
        ctx.close();
    }

    @GetMapping("/hello6")
    public void hello6() throws ExecutionException, InterruptedException {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
        Future<User> q1 = userService.getUserById(99);
        Future<User> q2 = userService.getUserById(98);
        Future<User> q3 = userService.getUserById(97);
        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        Thread.sleep(2000);
        Future<User> q4 = userService.getUserById(96);
        User u4 = q4.get();
        System.out.println(u4);
        ctx.close();
    }
}
