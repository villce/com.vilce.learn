package com.vilce.consul.config.consul;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Consul自定义配置
 * @projectName: com.eastmoney.emis.statistic
 * @package: com.eastmoney.emis.statistic.config.consul.ConsulConfig
 * @author: 韩庆瑞
 * @date: 2020/6/5 1:32 PM
 * @version: 1.0
 */
@Configuration
public class ConsulConfig {
    @Autowired(required = false)
    private TtlScheduler ttlScheduler;

    @Bean
    public VilceConsulServiceRegistry consulServiceRegistry(
            ConsulClient consulClient,
            ConsulDiscoveryProperties properties,
            HeartbeatProperties heartbeatProperties) {
        return new VilceConsulServiceRegistry(
                consulClient, properties, ttlScheduler, heartbeatProperties);
    }
}
