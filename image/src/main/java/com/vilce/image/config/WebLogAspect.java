package com.vilce.image.config;

import com.vilce.common.utils.LoggerUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.image.config.WebLogAspect
 * @Author: 雷才哲
 * @Date: 2019/12/9 15:31
 * @Version: 1.0
 */
//@Aspect
//@Component
public class WebLogAspect {

    //两个..代表所有子目录，最后括号里的两个..代表所有参数
    @Pointcut("execution(public * com.vilce.image.controller.*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        info("请求地址 : " + request.getRequestURL().toString());
        info("HTTP METHOD : " + request.getMethod());
        info("IP : " + request.getRemoteAddr());
        info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        info("参数 : " + Arrays.toString(joinPoint.getArgs()));
        // loggger.info("参数 : " + joinPoint.getArgs());

    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        info("返回值 : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }

    public void info(String str){
        LoggerUtils.info(WebLogAspect.class,str);
    }
}
