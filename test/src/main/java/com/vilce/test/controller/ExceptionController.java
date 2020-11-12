package com.vilce.test.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.controller.ExceptionController
 * @Author: 雷才哲
 * @Date: 2020/8/21 16:38
 * @Version: 1.0
 */
@Api(tags = "异常控制器")
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("500")
    public String get500Ex(HttpServletRequest request, HttpServletResponse response){
        int a = 3/0;
        request.setAttribute("message","500测试");
        return "test";
    }
}
