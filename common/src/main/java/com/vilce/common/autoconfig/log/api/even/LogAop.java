package com.vilce.common.autoconfig.log.api.even;

/**
 * @Description: AOP日志信息
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.api.even.LogAop
 * @Author: 雷才哲
 * @Date: 2020/11/12 13:39
 * @Version: 1.0
 */
public class LogAop<T> {

    /**
     * 日志等级
     */
    private String logLevel;
    /**
     * AOP 切口，日志类
     */
    private Class aClass;
    /**
     * 日志信息
     */
    private T logInfo;

    public LogAop(String logLevel, Class aClass, T logInfo) {
        this.logLevel = logLevel;
        this.aClass = aClass;
        this.logInfo = logInfo;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public T getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(T logInfo) {
        this.logInfo = logInfo;
    }
}
