package com.vilce.actuator.security.controller;

import com.vilce.actuator.security.model.User;
import com.vilce.actuator.security.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.controller.TestController
 * @Author: 雷才哲
 * @Date: 2020/6/22 13:33
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/getUser")
    public User getUser(){
        return testService.getUser();
    }
}
