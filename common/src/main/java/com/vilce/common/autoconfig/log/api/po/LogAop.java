package com.vilce.common.autoconfig.log.api.po;

import com.vilce.common.model.log.po.BaseLog;

/**
 * @Description: 接口访问拦截日志
 * @ProjectName: com.eastmoney.emis.utils
 * @Package: com.eastmoney.emis.autoconfigure.aop.log.po.LogAopService
 * @Author: 雷才哲
 * @Date: 2020/11/27 13:55
 * @Version: 1.0
 */
public class LogAop extends BaseLog {
    /**
     * 耗时
     */
    private long spentTime;
    /**
     * 响应结果大小
     */
    private String responseSize;
    /**
     * 响应结果
     */
    private Object responseBody;
    /**
     * 异常信息
     */
    private String exception;

    public long getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(long spentTime) {
        this.spentTime = spentTime;
    }

    public String getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(String responseSize) {
        this.responseSize = responseSize;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
