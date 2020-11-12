package com.vilce.common.autoconfig.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.consul.config.ConsulConfigBootstrapConfiguration;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.consul.VilceConsulConfigBootstrapConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/9 16:15
 * @Version: 1.0
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(ConsulConfigBootstrapConfiguration.class)
@ConditionalOnProperty(name = "spring.cloud.consul.config.enabled", havingValue = "true", matchIfMissing = true)
public class VilceConsulConfigBootstrapConfiguration implements InitializingBean {

    @Autowired
    private ConsulClient consul;

    public VilceConsulConfigBootstrapConfiguration(ConsulClient consul) {
        this.consul = consul;
    }

    @Primary
    @Bean("vilceConsulPropertySourceLocator")
    public VilceConsulPropertySourceLocator vilceConsulPropertySourceLocator(
            ConsulConfigProperties consulConfigProperties) {
        return new VilceConsulPropertySourceLocator(this.consul, consulConfigProperties);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtils.info(VilceConsulConfigBootstrapConfiguration.class, "【自动化配置】----springcloud config乱码解决组件初始化完成...");
    }

}
