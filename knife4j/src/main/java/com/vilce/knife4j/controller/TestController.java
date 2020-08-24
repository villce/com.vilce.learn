package com.vilce.knife4j.controller;

import com.vilce.knife4j.model.User;
import com.vilce.knife4j.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.controller.TestController
 * @Author: 雷才哲
 * @Date: 2020/6/22 13:33
 * @Version: 1.0
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "获取用户")
    @GetMapping("/getUser")
    public User getUser(){
        return testService.getUser();
    }

    @ApiOperation(value = "查询用户")
    @GetMapping("/findUser")
    public User findUser(User user) {
        return testService.findUser(user);
    }
}
