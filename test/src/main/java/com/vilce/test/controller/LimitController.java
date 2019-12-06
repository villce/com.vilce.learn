package com.vilce.test.controller;

import com.vilce.test.model.po.User;
import com.vilce.test.service.LimitService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.controller.LimitController
 * @Author: 雷才哲
 * @Date: 2019/11/25 15:58
 * @Version: 1.0
 */
@RestController
@RequestMapping("/limit")
@Api(tags = "ip限制访问接口")
public class LimitController {

    @Autowired
    private LimitService limitService;

    @GetMapping("ipLimiter")
    public String ipLimiter(HttpServletRequest request) {
        return limitService.getIPAddr(request);
    }

    @GetMapping("nameLimiter")
    public String nameLimiter(User user){
        return limitService.nameLimiter(user);
    }
}
