package com.vilce.common.config.advice;

import com.google.common.util.concurrent.RateLimiter;
import com.vilce.common.model.annotation.RateLimit;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.enums.TimeEnum;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.vo.BaseReq;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.LoggerUtils;
import com.vilce.common.utils.PhoneUtils;
import com.vilce.common.utils.RequestUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 在controller接口被访问前，进行相应的Advice增强
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.advice.ControllerAdviceInterceptor
 * @Author: 雷才哲
 * @Date: 2019/12/5 15:39
 * @Version: 1.0
 */
@Component
public class ControllerAdviceInterceptor implements MethodInterceptor {
    /**
     * 换行符
     */
    private static final String NEW_LINE = "\n";
    /**
     * 毫秒
     */
    private static final String MILLI_SECOND = "ms";
    /**
     * 控制器
     */
    private static final String MSG_CONTROLLER = "类|方法 ：";
    /**
     * 访问URL
     */
    private static final String MSG_ACCESS_URL = "访问URL ：";
    /**
     * 请求Method
     */
    public static final String MSG_METHOD = "Method  ：";
    /**
     * 请求PARAM
     */
    private static final String MSG_PARAMS = "请求参数：";
    /**
     * 耗时
     */
    private static final String MSG_TIME = "耗  时  ：";
    /**
     * 返回结果
     */
    private static final String MSG_RETURN_VALUE = "返回结果：";
    /**
     * 异常
     */
    private static final String MSG_EXCEPTION = "异  常  ：";

    /**
     * 用来存放不同接口不同ip的RateLimiter(key为客户端ip与接口名称，value为RateLimiter)
     */
    private ConcurrentHashMap<String, RateLimiter> rateLimitMap = new ConcurrentHashMap<>();
    private RateLimiter rateLimiter;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        if (method.isAnnotationPresent(RateLimit.class)) {
            //进行接口限流
            return rateLimitHandler(invocation);
        }else {
            //控制器请求aop处理
            return controllerLog(invocation);
        }
    }

    private Object rateLimitHandler(MethodInvocation invocation) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取Method对象
        Method method = invocation.getMethod();
        //获取注解信息
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        double limitNum = rateLimit.limitNum();
        //获取注解中限制对象
        String limitName = rateLimit.limitName();
        //获取注解中限制时间单位
        TimeEnum timeEnum = rateLimit.timeUnit();
        //获取请求参数，且该参数获取必须在proceed之前
        Object[] arguments = invocation.getArguments();
        String limitObject = null;
        if (ObjectUtils.isNotEmpty(arguments)) {
            if (request.equals(arguments[0])){
                //todo 对HttpServletRequest内部限制指定
            }else {
                Class paramClass = arguments[0].getClass();
                if (isBasicClass(paramClass)) {
                    if (ObjectUtils.isNotEmpty(limitName)) {
                        limitObject = String.valueOf(arguments[0]);
                    }
                } else {
                    Map<String, Object> map = JSONUtils.toJavaBean(JSONUtils.toJson(arguments[0]), Map.class);
                    limitObject = String.valueOf(map.get(limitName));
                }
            }
        }
        //注解所在方法名区分不同的限流策略
        String functionName = method.getName();
        String key = StringUtils.join(limitObject, ":", functionName);
        //获取rateLimiter
        if (rateLimitMap.containsKey(key)) {
            rateLimiter = rateLimitMap.get(key);
        } else {
            //根据时间单位计算限制次数
            if (TimeEnum.MINUTE.equals(timeEnum)) {
                limitNum /= 60;
            } else if (TimeEnum.HOUR.equals(timeEnum)) {
                limitNum /= 3600;
            } else if (TimeEnum.DAY.equals(timeEnum)) {
                limitNum /= 86400;
            }
            rateLimitMap.put(key, RateLimiter.create(limitNum));
            rateLimiter = rateLimitMap.get(key);
        }
        try {
            if (rateLimiter.tryAcquire()) {
                Object obj = invocation.proceed();
                return obj;
            }else {
                //拒绝请求（服务降级）
                throw new BasicException(ResultStatus.error_search_failed.getErrorCode(),"访问过于频繁，请稍后再试！");
            }
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    private Object controllerLog(MethodInvocation invocation) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String,Object> paramsMap = getRequestParam(invocation,request);
        //新建计时器并开始计时
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            //调用升级的action方法
            Object result = invocation.proceed();
            //暂停计时
            stopWatch.stop();
            //耗时
            long spentTime = (stopWatch.getTime() == 0) ? 1 : stopWatch.getTime();
            //打印INFO日志
            logInfo(invocation,request,paramsMap,result,spentTime);
            return result;
        } catch (Throwable e) {
            //暂停计时
            if (stopWatch.isStarted() || stopWatch.isSuspended()) {
                stopWatch.stop();
            }
            //耗时
            long spentTime = (stopWatch.getTime() == 0) ? 1 : stopWatch.getTime();
            //打印ERROR日志
            logError(invocation, request, paramsMap, spentTime, e);
            throw e;
        }
    }

    private void logInfo(MethodInvocation invocation, HttpServletRequest request, Map<String, Object> paramsMap, Object result, long spentTime){
        String log = StringUtils.join(NEW_LINE, MSG_CONTROLLER, invocation.getThis().getClass(), ".", invocation.getMethod().getName(), NEW_LINE);
        log = StringUtils.join(log, MSG_ACCESS_URL, request.getRequestURL(), NEW_LINE);
        log = StringUtils.join(log, MSG_METHOD, request.getMethod(), NEW_LINE);
        log = StringUtils.join(log, MSG_PARAMS, paramsMap, NEW_LINE);
        log = StringUtils.join(log, MSG_TIME, spentTime, MILLI_SECOND, NEW_LINE);
        if (ObjectUtils.isEmpty(result)) {
            log = StringUtils.join(log, MSG_RETURN_VALUE, result, NEW_LINE);
        } else if (result instanceof ResponseEntity) {
            log = StringUtils.join(log, MSG_RETURN_VALUE, JSONUtils.toJson(((ResponseEntity) result).getBody()), NEW_LINE);
        } else {
            log = StringUtils.join(log, MSG_RETURN_VALUE, JSONUtils.toJson(result), NEW_LINE);
        }
        LoggerUtils.info(invocation.getThis().getClass(), log);
    }

    private void logError(MethodInvocation invocation, HttpServletRequest request, Map<String, Object> paramsMap, long spentTime, Throwable e){
        String log = StringUtils.join(NEW_LINE, MSG_CONTROLLER, invocation.getThis().getClass(), ".", invocation.getMethod().getName(), NEW_LINE);
        log = StringUtils.join(log, MSG_ACCESS_URL, request.getRequestURL(), NEW_LINE);
        log = StringUtils.join(log, MSG_METHOD, request.getMethod(), NEW_LINE);
        log = StringUtils.join(log, MSG_PARAMS, paramsMap, NEW_LINE);
        log = StringUtils.join(log, MSG_TIME, spentTime, MILLI_SECOND, NEW_LINE);
        if (e instanceof BasicException) {
            BasicException basicException = (BasicException) e;
            log = StringUtils.join(log, MSG_EXCEPTION, e,  " 【statusCode】", basicException.getStatusCode(), ", 【errorMessage】", basicException.getErrorMessage(), NEW_LINE);
        } else {
            log = StringUtils.join(log, MSG_EXCEPTION, e.getStackTrace()[0], " ", e, NEW_LINE);
        }
        LoggerUtils.error(invocation.getThis().getClass(), log);
    }
    /**
     * @Description 获取请求参数
     * @Version 1.0
     */
    private Map<String, Object> getRequestParam(MethodInvocation invocation, HttpServletRequest request) {
        Map<String, Object> paramMap = new LinkedHashMap<>();
        Object[] args = invocation.getArguments();
        Method method = invocation.getMethod();
        Parameter[] parameters = method.getParameters();
        if (ArrayUtils.isEmpty(parameters)) {
            return null;
        }
        for (int i = 0; i < parameters.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                Enumeration<String> params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    String key = params.nextElement();
                    paramMap.put(key, request.getParameter(key));
                }
            } else if (!(args[i] instanceof HttpServletResponse)) {
                if (args[i] instanceof BaseReq) {
                    BaseReq baseReq = (BaseReq) args[i];
                    //客户端IP
                    String clientIp = RequestUtils.getClientIp(request);
                    //IP地址
                    baseReq.setClientIp(clientIp);
                    //将用户信息设置如HttpServletRequest中
                    request.setAttribute(parameters[i].getName(), baseReq);
                    paramMap.put(parameters[i].getName(), JSONUtils.toJson(baseReq));
                } else {
                    paramMap.put(parameters[i].getName(), JSONUtils.toJson(args[i]));
                }
            }
        }
        return paramMap;
    }

    public boolean isBasicClass(Class obj) {
        if (obj.equals(java.lang.Integer.class) ||
                obj.equals(java.lang.String.class) ||
                obj.equals(java.lang.Byte.class) ||
                obj.equals(java.lang.Long.class) ||
                obj.equals(java.lang.Double.class) ||
                obj.equals(java.lang.Float.class) ||
                obj.equals(java.lang.Character.class) ||
                obj.equals(java.lang.Short.class) ||
                obj.equals(java.lang.Boolean.class)) {
            return true;
        }
        return false;
    }
}
