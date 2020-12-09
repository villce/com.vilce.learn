package com.vilce.consul.service.impl;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.log.utils.LoggerUtils;
import com.vilce.consul.service.ConsulService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.service.impl.ConsulServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/12/9 11:04
 * @Version: 1.0
 */
@Service
public class ConsulServiceImpl implements ConsulService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConsulClient consulClient;
    @Value("${spring.vilce.consulUrl}")
    private String consulUrl;
    private static final String token = "X-Consul-Token";
    private static final String admin = "461ae90d-fc3e-462f-8ce1-a46f542183ec";

    @Override
    public String deregisterById(String serviceId) {
        String url = StringUtils.join(consulUrl, "/v1/agent/service/deregister/", serviceId);
        HttpHeaders headers = new HttpHeaders();
        headers.set(token, admin);
        HttpEntity httpEntity = new HttpEntity(headers);
        try {
            restTemplate.put(url, httpEntity);
            return StringUtils.join("删除：", serviceId, " 成功!");
        }catch (Exception e) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), StringUtils.join("删除：", serviceId, " 失败!"));
        }
    }

    @Override
    public String deregisterByName(String serviceName) {
        HealthServicesRequest request = HealthServicesRequest.newBuilder()
                .setTags(null)
                .setPassing(false)
                .setQueryParams(null)
                .setToken(null)
                .build();
        List<HealthService> response = consulClient.getHealthServices(serviceName, request).getValue();
        for(HealthService service : response) {
            // 创建一个用来剔除无效实例的ConsulClient，连接到无效实例注册的agent
            ConsulClient clearClient = new ConsulClient(service.getNode().getAddress(), 8500);
            service.getChecks().forEach(check -> {
                if(check.getStatus() != Check.CheckStatus.PASSING) {
                    LoggerUtils.info(ConsulServiceImpl.class, "unregister : " + check.getServiceId());
                    clearClient.agentServiceDeregister(check.getServiceId());
                }
            });
        }
        return StringUtils.join("移除：", serviceName, "成功!");
    }
}
