package com.dpf.eurekaprovider.controller;

import com.dpf.commmons.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    /**
     * 注意重定向的地址一定要写绝对地址，不要写相对地址，不然会出问题
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String register(User user){
        return "redirect:http://eureka-provider/loginPage?username="+user.getUsername();
    }

    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(String username){
       return "loginPage:"+username;
    }
}
