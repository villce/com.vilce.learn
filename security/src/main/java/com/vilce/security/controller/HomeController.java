package com.vilce.security.controller;

import com.vilce.security.model.UserDO;
import com.vilce.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.controller.HomeController
 * @Author: 雷才哲
 * @Date: 2019/12/10 19:48
 * @Version: 1.0
 */
@Controller
@AllArgsConstructor
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(UserDO userDO){
        // 此处省略校验逻辑
        userService.insert(userDO);
        return "redirect:register?success";
    }
}
