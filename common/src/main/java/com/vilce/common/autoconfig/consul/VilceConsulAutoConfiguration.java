package com.vilce.common.autoconfig.consul;

import com.vilce.common.model.log.utils.LoggerUtils;
import com.vilce.common.utils.SpecialCharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.UtilAutoConfiguration;
import org.springframework.cloud.consul.ConsulAutoConfiguration;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClientConfiguration;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.UUID;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.consul.VilceConsulAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/12/1 11:04
 * @Version: 1.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ConsulDiscoveryClientConfiguration.class)
@AutoConfigureBefore(ConsulDiscoveryClientConfiguration.class)
@AutoConfigureAfter({UtilAutoConfiguration.class, ConsulAutoConfiguration.class})
public class VilceConsulAutoConfiguration implements CommandLineRunner {
    private Environment environment;

    public VilceConsulAutoConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsulDiscoveryProperties consulDiscoveryProperties(InetUtils inetUtils) {
        ConsulDiscoveryProperties properties = new ConsulDiscoveryProperties(inetUtils);
        //自定义生成实例ID, 实例名称不可以以数字开头
        properties.setInstanceId(StringUtils.join("vilce", SpecialCharUtils.LINE_THROUGH_CENTER, UUID.randomUUID().toString()));
        //表示注册服务时使用IP而不是hostname，默认：false
        properties.setPreferIpAddress(true);
        //IP地址
        properties.setIpAddress(environment.getProperty("JAVA_LOCALIP", "localhost"));
        return properties;
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.info(VilceConsulConfigBootstrapConfiguration.class, "【自动化配置】---consul注册serverId组件初始化完成...");
    }
}
