package com.vilce.test.controller;

import com.vilce.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.controller.TestController
 * @Author: 雷才哲
 * @Date: 2020/11/12 14:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试控制器")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("text")
    @ApiOperation(value = "中文测试")
    public String text() {
        return testService.text();
    }

    @PostMapping("log")
    @ApiOperation(value = "日志测试")
    public String log(String name) {
        return testService.log(name);
    }

    @GetMapping("log1")
    @ApiOperation(value = "日志测试1")
    public String log1(String name) {
        return testService.log(name);
    }

    @GetMapping("log2/{name}")
    @ApiOperation(value = "日志测试2")
    public String log2(@PathVariable String name) {
        return testService.log(name);
    }
}
