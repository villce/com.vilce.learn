package com.vilce.consul.config.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @description: Consul自定义配置
 * @projectName: com.eastmoney.emis.statistic
 * @package: com.eastmoney.emis.statistic.config.consul.VilceConsulServiceRegistry
 * @author: 韩庆瑞
 * @date: 2020/6/5 1:32 PM
 * @version: 1.0
 */

public class VilceConsulServiceRegistry extends ConsulServiceRegistry {
    @Autowired
    private Environment env;
    @Value("${spring.vilce.token.system-number}")
    private String serviceNo;

    public VilceConsulServiceRegistry(ConsulClient client, ConsulDiscoveryProperties properties, TtlScheduler ttlScheduler, HeartbeatProperties heartbeatProperties) {
        super(client, properties, ttlScheduler, heartbeatProperties);
    }

    @Override
    public void register(ConsulRegistration reg) {
        // 重新设计id，此处用的是名字也可以用其他方式例如instanceid、host、uri等
        reg.getService().setId(UUID.randomUUID().toString());
        String localIp = env.getProperty("JAVA_LOCALIP");
        int applicationPort = Integer.valueOf(env.getProperty("JAVA_APPLICATIONPORT"));
        reg.getService().setAddress(localIp);
        reg.getService().setPort(applicationPort);
        reg.getService().setName(serviceNo);
        reg.getService().setTags(new ArrayList<String>() {{
            this.add("dataTag_ConsulTest");
        }});
        //使用代码注册后，yml配置中的注册信息则会失效，需要自己添加健康检查等配置

        NewService.Check check = new NewService.Check();
        check.setHttp("http://" + localIp + ":" + applicationPort + "/health");
        check.setInterval("30s");
        check.setTimeout("1s");
        check.setMethod("GET");
        reg.getService().setCheck(check);
        super.register(reg);
    }
}
