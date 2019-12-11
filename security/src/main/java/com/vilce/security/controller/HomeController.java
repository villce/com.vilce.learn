package com.vilce.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.controller.HomeController
 * @Author: 雷才哲
 * @Date: 2019/12/10 19:48
 * @Version: 1.0
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
