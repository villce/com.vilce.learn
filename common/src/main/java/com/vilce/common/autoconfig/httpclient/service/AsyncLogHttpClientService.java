package com.vilce.common.autoconfig.httpclient.service;

import com.vilce.common.autoconfig.httpclient.po.AsyncLogHttpClient;

/**
 * @Description: 第三方http访问日志服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.httpclient.service.AsyncLogHttpClientService
 * @Author: 雷才哲
 * @Date: 2020/11/12 15:49
 * @Version: 1.0
 */
public interface AsyncLogHttpClientService {

    /**
     * @Description 记录请求信息
     * @Version 1.0
     */
    void traceRequest(AsyncLogHttpClient asyncLogHttpClient);

    /**
     * @Description 记录响应信息
     * @Version 1.0
     */
    void traceResponse(AsyncLogHttpClient asyncLogHttpClient);
}
