package com.vilce.common.model.exception;

import com.vilce.common.model.enums.ResultStatus;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.exception.BasicException
 * @Author: 雷才哲
 * @Date: 2019/12/6 15:41
 * @Version: 1.0
 */
public class BasicException extends RuntimeException{
    private int statusCode;
    private String errorMessage;

    public BasicException() {
        this.statusCode = ResultStatus.FAIL.getErrorCode();
        this.errorMessage = ResultStatus.FAIL.getErrorMsg();
    }

    public BasicException(ResultStatus status) {
        this.statusCode = status.getErrorCode();
        this.errorMessage = status.getErrorMsg();
    }

    public BasicException(int status, String message) {
        this.statusCode = status;
        this.errorMessage = message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
