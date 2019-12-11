package com.vilce.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.controller.UserController
 * @Author: 雷才哲
 * @Date: 2019/12/10 19:49
 * @Version: 1.0
 */
@Controller
public class UserController {

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        return "user/user";
    }

}
