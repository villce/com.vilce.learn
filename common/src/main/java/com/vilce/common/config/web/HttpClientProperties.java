package com.vilce.common.config.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.config.web.HttpClientProperties
 * @Author: 雷才哲
 * @Date: 2020/11/6 17:57
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "com.vilce.http-client")
public class HttpClientProperties {

    /**
     * 读取超时默认设为5s
     */
    private Integer readTimeOut = 5000;
    /**
     * 连接超时默认设为10s
     */
    private Integer connectTimeOut = 10000;

    public Integer getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(Integer readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public Integer getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(Integer connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }
}
