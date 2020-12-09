package com.vilce.consul.service;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.service.ConsulService
 * @Author: 雷才哲
 * @Date: 2020/12/9 11:04
 * @Version: 1.0
 */
public interface ConsulService {

    /**
     * 根据serviceId删除无用服务
     *
     * @param serviceId
     */
    String deregisterById(String serviceId);

    /**
     * 根据serviceName删除无用服务
     *
     * @param serviceName
     */
    String deregisterByName(String serviceName);
}
