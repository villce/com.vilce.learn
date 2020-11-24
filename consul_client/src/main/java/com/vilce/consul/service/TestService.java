package com.vilce.consul.service;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.service.TestService
 * @Author: 雷才哲
 * @Date: 2020/11/23 15:24
 * @Version: 1.0
 */
public interface TestService {
    List<ServiceInstance> activity();
}
