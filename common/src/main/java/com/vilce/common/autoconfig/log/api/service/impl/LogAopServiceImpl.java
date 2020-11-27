package com.vilce.common.autoconfig.log.api.service.impl;

import com.google.common.collect.Maps;
import com.vilce.common.autoconfig.log.api.MethodLogInterceptor;
import com.vilce.common.autoconfig.log.api.po.LogAop;
import com.vilce.common.autoconfig.log.api.service.LogAopService;
import com.vilce.common.model.log.utils.LoggerUtils;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.ObjectSizeUtil;
import com.vilce.common.utils.RequestUtils;
import com.vilce.common.utils.SpecialCharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @Description: Description
 * @ProjectName: com.eastmoney.emis.utils
 * @Package: com.eastmoney.emis.autoconfigure.aop.log.service.impl.LogAopServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/11/27 14:01
 * @Version: 1.0
 */
@Service
public class LogAopServiceImpl implements LogAopService {

    @Override
    public LogAop initLogAop() {
        HttpServletRequest request = RequestUtils.getRequest();
        HttpServletResponse response = RequestUtils.getResponse();
        //创建拦截日志信息
        LogAop logAop = new LogAop();
        String tId = UUID.randomUUID().toString();
        RequestUtils.getRequest().setAttribute("T_ID", tId);
        //生成事物流水号
        logAop.settId(tId);
        //协议
        logAop.setProtocol(RequestUtils.getRequest().getProtocol());
        //请求URL
        logAop.setRequestUrl(StringUtils.substringBefore(String.valueOf(RequestUtils.getRequest().getRequestURL()), SpecialCharUtils.ASK_SIGN_EN));
        //请求方法
        logAop.setMethod(RequestUtils.getRequest().getMethod());
        //请求参数
        logAop.setRequestParams(RequestUtils.getParameterMap(RequestUtils.getRequest()));
        //请求类型 ContentType
        logAop.setContentType(Objects.nonNull(RequestUtils.getRequest().getContentType()) ? RequestUtils.getRequest().getContentType() : null);
        return logAop;
    }

    @Override
    @Async
    public void logInfo(LogAop logAop) {
        //响应请求信息日志集合
        Map<String, Object> logMap = Maps.newLinkedHashMap();
        logMap.put("接口ID", logAop.gettId());
        logMap.put("访问URL", logAop.getRequestUrl());
        logMap.put("访问方法", logAop.getMethod());
        logMap.put("入参", logAop.getRequestParams());
        logMap.put("请求类型", logAop.getContentType());
        logMap.put("协议", logAop.getProtocol());
        logMap.put("响应耗时", StringUtils.join((logAop.getSpentTime() == 0) ? 1 : logAop.getSpentTime(), "ms"));
        logMap.put("响应大小", ObjectSizeUtil.getObjectSizeUnit(logAop.getResponseBody()));
        logMap.put("响应结果", logAop.getResponseBody());
        if (LoggerUtils.isDebug()) {
            LoggerUtils.info(MethodLogInterceptor.class, JSONUtils.toJSONPrettyString(logMap));
        } else {
            LoggerUtils.info(MethodLogInterceptor.class, JSONUtils.toJSONString(logMap));
        }
    }

    @Override
    @Async
    public void logError(LogAop logAop) {
        //响应请求信息日志集合
        Map<String, Object> errorLogMap = Maps.newLinkedHashMap();
        errorLogMap.put("接口ID", logAop.gettId());
        errorLogMap.put("访问URL", logAop.getRequestUrl());
        errorLogMap.put("访问方法", logAop.getMethod());
        errorLogMap.put("入参", logAop.getRequestParams());
        errorLogMap.put("请求类型", logAop.getContentType());
        errorLogMap.put("协议", logAop.getProtocol());
        errorLogMap.put("异常", logAop.getException());
        if (LoggerUtils.isDebug()) {
            LoggerUtils.error(MethodLogInterceptor.class, JSONUtils.toJSONPrettyString(errorLogMap));
        } else {
            LoggerUtils.error(MethodLogInterceptor.class, JSONUtils.toJSONString(errorLogMap));
        }
    }
}
