package com.vilce.test.controller;

import com.vilce.test.model.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.controller.LeanController
 * @Author: 雷才哲
 * @Date: 2020/9/2 11:18
 * @Version: 1.0
 */
@RestController
public class LeanController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "login")
    public Map<String,Object> login(@RequestBody User user){
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=user.getName();
        String password=user.getPassword();
        if(!userName.equals("") && password!=""){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }
}
