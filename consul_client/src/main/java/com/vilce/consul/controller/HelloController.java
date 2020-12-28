package com.vilce.consul.controller;

import com.vilce.common.autoconfig.redis.annotation.RedisFilter;
import com.vilce.consul.config.event.MyEvent;
import com.vilce.consul.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.controller.HelloController
 * @Author: 雷才哲
 * @Date: 2020/9/14 16:01
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private TestService testService;
    @Resource
    private ApplicationContext applicationContext;

    @Value("${lcz.test}")
    private String str;

    @GetMapping("hello")
    public String hello() {
        boolean result = true;
        MyEvent myEvent = new MyEvent(this, result);
        applicationContext.publishEvent(myEvent);
        return str;
    }

    @GetMapping("hello2")
    public String hello2() {
        return "触发监听成功";
    }

    @RequestMapping("activity")
    public List<ServiceInstance> activity() {
        return testService.activity();
    }
}
