package com.vilce.consul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.controller.HelloController
 * @Author: 雷才哲
 * @Date: 2020/9/14 16:01
 * @Version: 1.0
 */
@RestController
public class HelloController {

    @Value("${lcz.test}")
    private String str;

    @RequestMapping("/hello")
    public String hello() {
        return str;
    }
}
