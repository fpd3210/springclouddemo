package com.dpf.api;

import com.dpf.commmons.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author dpf
 * @create 2020-03-31 22:04
 * @email 446933040@qq.com
 */
public interface IUserService {
    @GetMapping("/hello")
    String hello();

    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    @PostMapping("/user2")
    User add2(@RequestBody User user);

    @DeleteMapping("/user2/{id}")
    void deleteUser2(@PathVariable("id") Integer id);

    @GetMapping("/user3")
    void getUserByName(@RequestHeader("name") String name);
}
