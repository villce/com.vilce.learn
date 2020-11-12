package com.vilce.common.autoconfig.log.interceptor;

import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.vo.BaseRequest;
import com.vilce.common.utils.HiddenUtils;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 日志拦截
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.interceptor.MethodLogInterceptor
 * @Author: 雷才哲
 * @Date: 2020/9/3 9:51
 * @Version: 1.0
 */
@Slf4j
public class MethodLogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取接口访问地址
        String requestUrl = request.getRequestURL().toString();
        // 获取传参
        Map<String, Object> params = getRequestParam(methodInvocation);
        // 启动计时器
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            // 继续访问方法
            Object obj = methodInvocation.proceed();
            // 关闭计时器，获取响应时间
            stopWatch.stop();
            long spendTime = stopWatch.getTime();
            // 记录INFO日志
            logInfo(requestUrl, params, spendTime, obj);
            return obj;
        } catch (Throwable e) {
            // 当访问异常时
            // 关闭计时器，获取响应时间
            stopWatch.stop();
            long spendTime = stopWatch.getTime();
            // 记录ERROR日志
            logError(requestUrl, params, spendTime, e);
            throw e;
        }
    }

    /**
     * Info日志打印
     *
     * @param requestUrl
     * @param params
     * @param spendTime
     * @param obj
     */
    private void logInfo(String requestUrl, Map<String, Object> params, long spendTime, Object obj) {
        Map<String, Object> logMap = new LinkedHashMap<>();
        logMap.put("访问地址", requestUrl);
        logMap.put("响应耗时", spendTime);
        logMap.put("传入参数", params);
        logMap.put("返回数据", obj);
        log.info(JSONUtils.toJsonPretty(logMap));
    }

    /**
     * Error日志打印
     *
     * @param requestUrl
     * @param params
     * @param spendTime
     * @param e
     */
    private void logError(String requestUrl, Map<String, Object> params, long spendTime, Throwable e) {
        Map<String, Object> logMap = new LinkedHashMap<>();
        logMap.put("访问地址", requestUrl);
        logMap.put("响应耗时", spendTime);
        logMap.put("传入参数", params);
        if (e instanceof BasicException) {
            logMap.put("异常", StringUtils.join(e, ",【statusCode】：", ((BasicException) e).getStatusCode(), ",【errorMessage】:",
                    ((BasicException) e).getErrorMessage()));
        } else {
            logMap.put("异常", StringUtils.join(e.getStackTrace()[0], " ", e.getMessage()));
        }
        log.error(JSONUtils.toJsonPretty(logMap));
    }

    /**
     * 获取请求参数
     *
     * @param invocation
     * @return
     */
    private Map<String, Object> getRequestParam(MethodInvocation invocation) {
        Object[] args = invocation.getArguments();
        Method method = invocation.getMethod();
        Parameter[] parameters = method.getParameters();
        if (ArrayUtils.isEmpty(parameters)) {
            return Collections.emptyMap();
        }
        Map<String, Object> paramMap = new LinkedHashMap<>();
        HttpServletRequest request = RequestUtils.getRequest();
        for (int i = 0; i < parameters.length; i++) {
            if (args[i] instanceof HttpServletResponse) {
                continue;
            }
            if (args[i] instanceof HttpServletRequest) {
                Enumeration<String> params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    String key = params.nextElement();
                    paramMap.put(key, request.getParameter(key));
                }
            } else if (args[i] instanceof BaseRequest) {
                request.setAttribute(parameters[i].getName(), args[i]);
                paramMap.put(parameters[i].getName(), args[i]);
            } else if (args[i] instanceof MultipartFile) {
                paramMap.put(parameters[i].getName(), ((MultipartFile) args[i]).getOriginalFilename());
            } else if (args[i] instanceof File) {
                paramMap.put(parameters[i].getName(), ((File) args[i]).getPath());
            } else if (args[i] instanceof Throwable) {
                //参数信息异常，忽略
            } else {
                paramMap.put(parameters[i].getName(), args[i]);
            }
        }
        paramMap = HiddenUtils.hidden(paramMap, request.getRequestURI());
        return JSONUtils.toJavaBean(JSONUtils.toJsonPretty(paramMap), Map.class);
    }
}
