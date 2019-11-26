package com.vilce.annotation.api;

import com.vilce.annotation.service.LimitService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.api.LimitController
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

    @GetMapping("/iplimiter")
    public String ipLimiter(HttpServletRequest request) {
        return limitService.getIPAddr(request);
    }
}
