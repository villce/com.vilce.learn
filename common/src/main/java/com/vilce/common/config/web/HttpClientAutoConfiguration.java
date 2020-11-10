package com.vilce.common.config.web;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.config.web.HttpClientAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/6 18:01
 * @Version: 1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {
}
