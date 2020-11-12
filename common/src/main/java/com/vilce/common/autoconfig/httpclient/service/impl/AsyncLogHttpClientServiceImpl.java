package com.vilce.common.autoconfig.httpclient.service.impl;

import com.google.common.collect.Maps;
import com.vilce.common.autoconfig.httpclient.interceptor.HttpClientInterceptor;
import com.vilce.common.autoconfig.httpclient.po.AsyncLogHttpClient;
import com.vilce.common.autoconfig.httpclient.service.AsyncLogHttpClientService;
import com.vilce.common.model.enums.DateEnum;
import com.vilce.common.model.log.utils.LoggerUtils;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 第三方http访问日志服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.httpclient.service.impl.AsyncLogHttpClientServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/11/12 15:49
 * @Version: 1.0
 */
@Service
public class AsyncLogHttpClientServiceImpl implements AsyncLogHttpClientService {

    /**
     * 第三方接口请求module name
     */
    private static final String THIRD_PARTY = "Third_Party";

    @Override
    public void traceRequest(AsyncLogHttpClient asyncLogHttpClient) {
        //请求日志记录集合
        Map<String, Object> logMap = Maps.newLinkedHashMap();
        logMap.put("ID", asyncLogHttpClient.gettId());
        logMap.put("请求时间", TimeUtils.dateToYMDHMS(asyncLogHttpClient.getRequestTime()));
        logMap.put("请求地址", asyncLogHttpClient.getRequestUrl());
        logMap.put("请求方法", asyncLogHttpClient.getMethod());
        logMap.put("请求入参", asyncLogHttpClient.getRequestParams());
        logMap.put("Content-Type", asyncLogHttpClient.getContentType());
        if (LoggerUtils.isDebug()) {
            LoggerUtils.module(HttpClientInterceptor.class, THIRD_PARTY, THIRD_PARTY, JSONUtils.toJSONPrettyString(logMap));
        } else {
            LoggerUtils.module(HttpClientInterceptor.class, THIRD_PARTY, THIRD_PARTY, JSONUtils.toJSONString(logMap));
        }
    }

    @Override
    public void traceResponse(AsyncLogHttpClient asyncLogHttpClient) {
        //响应请求信息日志集合
        Map<String, Object> logMap = Maps.newLinkedHashMap();
        logMap.put("ID", asyncLogHttpClient.gettId());
        logMap.put("响应时间", TimeUtils.dateToYMDHMS(asyncLogHttpClient.getRequestTime()));
        logMap.put("响应地址", asyncLogHttpClient.getRequestUrl());
        logMap.put("响应方法", asyncLogHttpClient.getMethod());
        logMap.put("请求入参", asyncLogHttpClient.getRequestParams());
        logMap.put("Content-Type", asyncLogHttpClient.getContentType());
        logMap.put("耗时", StringUtils.join((asyncLogHttpClient.getSpentTime() == 0) ? 1 : asyncLogHttpClient.getSpentTime(), "ms"));
        logMap.put("响应数据", asyncLogHttpClient.getResponseBody());
        if (LoggerUtils.isDebug()) {
            LoggerUtils.module(HttpClientInterceptor.class, THIRD_PARTY, THIRD_PARTY, JSONUtils.toJSONPrettyString(logMap));
        } else {
            LoggerUtils.module(HttpClientInterceptor.class, THIRD_PARTY, THIRD_PARTY, JSONUtils.toJSONString(logMap));
        }
    }
}
