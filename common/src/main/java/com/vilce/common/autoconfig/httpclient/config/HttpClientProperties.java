package com.vilce.common.autoconfig.httpclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: httpClient配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.httpclient.config.HttpClientProperties
 * @Author: 雷才哲
 * @Date: 2020/11/6 17:57
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "com.vilce.http-client")
public class HttpClientProperties {

    private boolean enable;
    /**
     * HttpClientService read timeout (in milliseconds),default:5000
     */
    private Integer readTimeOut = 5000;
    /**
     * HttpClientService connect timeout (in milliseconds),default:10000
     */
    private Integer connectTimeOut = 10000;
    /**
     * 开启调用接口拦截器
     */
    private boolean enableInterceptor = true;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

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

    public boolean isEnableInterceptor() {
        return enableInterceptor;
    }

    public void setEnableInterceptor(boolean enableInterceptor) {
        this.enableInterceptor = enableInterceptor;
    }
}
