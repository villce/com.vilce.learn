package com.vilce.common.autoconfig.log.api;

import ch.qos.logback.classic.Level;
import com.google.common.collect.Maps;
import com.vilce.common.autoconfig.log.api.even.LogAop;
import com.vilce.common.autoconfig.log.api.even.LogApplicationEvent;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.vo.BaseRequest;
import com.vilce.common.utils.HiddenUtils;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.RequestUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @Description: 日志拦截
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.api.MethodLogInterceptor
 * @Author: 雷才哲
 * @Date: 2020/9/3 9:51
 * @Version: 1.0
 */
public class MethodLogInterceptor implements MethodInterceptor {

    /**
     * 事件发布对象
     */
    private ApplicationEventPublisher eventPublisher;

    public MethodLogInterceptor(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 获取传参
        Map<String, Object> params = getRequestParam(invocation);
        // 启动计时器
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            // 继续访问方法
            Object result = invocation.proceed();
            // 关闭计时器，获取响应时间
            stopWatch.stop();
            LogAop logAop = null;
            long spendTime = stopWatch.getTime();
            if (Objects.nonNull(result) && (result instanceof ResponseEntity)) {
                Object resultBody = ((ResponseEntity) result).getBody();
                //打印INFO日志
                logAop = logInfo(invocation, params, spendTime, resultBody);
            } else {
                //打印INFO日志
                logAop = logInfo(invocation, params, spendTime, result);
            }
            eventPublisher.publishEvent(new LogApplicationEvent(logAop));
            return result;
        } catch (Throwable e) {
            // 当访问异常时
            // 关闭计时器，获取响应时间
            stopWatch.stop();
            long spendTime = stopWatch.getTime();
            // 记录ERROR日志
            LogAop logAop = logError(invocation, params, spendTime, e);
            eventPublisher.publishEvent(new LogApplicationEvent(logAop));
            throw e;
        }
    }

    /**
     * Info日志打印
     *
     * @param invocation 执行器
     * @param params     入参
     * @param spendTime  响应耗时
     * @param result     响应数据
     */
    private LogAop logInfo(MethodInvocation invocation, Map<String, Object> params, long spendTime, Object result) {
        HttpServletRequest request = RequestUtils.getRequest();
        Map<String, Object> logMap = Maps.newLinkedHashMap();
        logMap.put("方法类", StringUtils.join(invocation.getThis().getClass(), ".", invocation.getMethod().getName()));
        logMap.put("访问地址", request.getRequestURL());
        logMap.put("访问方法", request.getMethod());
        logMap.put("传入参数", CollectionUtils.isEmpty(params) ? Collections.emptyMap() : params);
        logMap.put("响应耗时", spendTime);
        logMap.put("响应数据", result);
        return new LogAop<>(Level.INFO.levelStr, invocation.getThis().getClass(), logMap);
    }

    /**
     * Error日志打印
     *
     * @param invocation 执行器
     * @param params     入参
     * @param spendTime  响应耗时
     * @param e          异常
     */
    private LogAop logError(MethodInvocation invocation, Map<String, Object> params, long spendTime, Throwable e) {
        HttpServletRequest request = RequestUtils.getRequest();
        Map<String, Object> logMap = Maps.newLinkedHashMap();
        logMap.put("方法类", StringUtils.join(invocation.getThis().getClass(), ".", invocation.getMethod().getName()));
        logMap.put("访问地址", request.getRequestURL());
        logMap.put("访问方法", request.getMethod());
        logMap.put("传入参数", params);
        logMap.put("响应耗时", spendTime);
        if (e instanceof BasicException) {
            logMap.put("异常", StringUtils.join(e, ",【statusCode】：", ((BasicException) e).getStatusCode(), ",【errorMessage】:",
                    ((BasicException) e).getErrorMessage()));
        } else {
            logMap.put("异常", StringUtils.join(e.getStackTrace()[0], " ", e.getMessage()));
        }
        return new LogAop<>(Level.ERROR.levelStr, invocation.getThis().getClass(), logMap);
    }

    /**
     * 获取请求参数
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
