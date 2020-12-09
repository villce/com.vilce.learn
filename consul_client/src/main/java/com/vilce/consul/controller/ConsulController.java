package com.vilce.consul.controller;

import com.vilce.consul.service.ConsulService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.controller.ConsulController
 * @Author: 雷才哲
 * @Date: 2020/12/9 11:01
 * @Version: 1.0
 */
@RestController
@Api(tags = "Consul相关控制器")
@RequestMapping("/consul")
public class ConsulController {

    @Autowired
    private ConsulService consulService;

    @GetMapping("deregisterById/{serviceId}")
    @ApiOperation(value = "根据serviceId删除无用服务（直接调用consul注销PUT方法）")
    public String deregisterById(@PathVariable String serviceId) {
        return consulService.deregisterById(serviceId);
    }

    @GetMapping("deregisterByName/{serviceName}")
    @ApiOperation(value = "根据serviceName删除无用服务")
    public String deregisterByName(@PathVariable String serviceName) {
        return consulService.deregisterByName(serviceName);
    }
}
