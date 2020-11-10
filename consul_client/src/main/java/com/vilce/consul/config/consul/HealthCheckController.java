package com.vilce.consul.config.consul;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: Description
 * @projectName: com.eastmoney.emis.statistic
 * @package: com.eastmoney.emis.statistic.common.HealthCheckController
 * @author: 韩庆瑞
 * @date: 2020/6/5 11:03 AM
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "health")
@Api(tags = "健康检查控制器")
public class HealthCheckController {

    @GetMapping
    public void record() {
        return;
    }
}

