package com.vilce.common.exception;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.exception.ServiceCustomException
 * @Author: 雷才哲
 * @Date: 2020/8/21 16:30
 * @Version: 1.0
 */
public class ServiceCustomException extends RuntimeException {
    private  Integer customStatus;
    private String customMessage;

    private static final long serialVersionUID = 1L;

    public ServiceCustomException() {
        super();
    }

    public ServiceCustomException(Integer customStatus ,String customMessage) {
        this.customStatus=customStatus;
        this.customMessage=customMessage;
    }

    public ServiceCustomException(Integer customStatus ,String customMessage,Throwable cause) {
        super(cause);
        this.customStatus=customStatus;
        this.customMessage=customMessage;
    }

    public Integer getCustomStatus() {
        return customStatus;
    }

    public void setCustomStatus(Integer customStatus) {
        this.customStatus = customStatus;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }
}
