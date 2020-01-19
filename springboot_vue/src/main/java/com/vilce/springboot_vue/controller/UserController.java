package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.vo.request.UserReq;
import com.vilce.springboot_vue.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

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

    private ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap();

    @CrossOrigin
    @PostMapping("login")
    public boolean login(@RequestBody UserReq req){
        return userService.login(req);
    }

    @GetMapping("test")
    public String test(){
        if (ObjectUtils.isEmpty(map.get("key"))){
            map.put("key",0);
        }else {
            Integer a = map.get("key");
            a = a+1;
            map.put("key",a);
            if (map.get("key")>3){
                return "成功！";
            }
        }
        throw new RuntimeException();
    }
}
