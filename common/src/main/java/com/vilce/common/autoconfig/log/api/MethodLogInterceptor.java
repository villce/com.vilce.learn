package com.vilce.common.autoconfig.log.api;

import com.vilce.common.autoconfig.log.api.po.LogAop;
import com.vilce.common.autoconfig.log.api.service.LogAopService;
import com.vilce.common.model.exception.BasicException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.http.ResponseEntity;
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
    private LogAopService logAopService;

    public MethodLogInterceptor(LogAopService logAopService) {
        this.logAopService = logAopService;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //创建拦截日志信息
        LogAop logAop = logAopService.initLogAop();
        // 启动计时器
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            // 继续访问方法
            Object result = invocation.proceed();
            // 关闭计时器，获取响应时间
            stopWatch.stop();
            long spendTime = stopWatch.getTime();
            logAop.setSpentTime(spendTime);
            if (Objects.nonNull(result) && (result instanceof ResponseEntity)) {
                Object resultBody = ((ResponseEntity) result).getBody();
                logAop.setResponseBody(resultBody);
            } else {
                logAop.setResponseBody(result);
            }
            //打印INFO日志
            logAopService.logInfo(logAop);
            return result;
        } catch (Throwable e) {
            // 当访问异常时
            // 关闭计时器，获取响应时间
            stopWatch.stop();
            long spendTime = stopWatch.getTime();
            logAop.setSpentTime(spendTime);
            if (e instanceof BasicException) {
                logAop.setException(StringUtils.join(e, ",【statusCode】：", ((BasicException) e).getStatusCode(), ",【errorMessage】:",
                        ((BasicException) e).getErrorMessage()));
            } else {
                logAop.setException(StringUtils.join(e.getStackTrace()[0], " ", e.getMessage()));
            }
            //打印ERROR日志
            logAopService.logError(logAop);
            throw e;
        }
    }
}
