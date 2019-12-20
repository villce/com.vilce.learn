package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.model.vo.request.UserReq;
import com.vilce.springboot_vue.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.UserController
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户API")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("login")
    public boolean login(@RequestBody UserReq req){
        return userService.login(req);
    }
}
