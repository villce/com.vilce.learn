package com.vilce.consul.service.impl;

import com.vilce.consul.service.TestService;
import com.vilce.consul.util.ReferenceServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.service.impl.TestServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/11/23 15:24
 * @Version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public List<ServiceInstance> activity() {
        return discoveryClient.getInstances(ReferenceServiceName.EMIS_Data_Activity);
    }
}
