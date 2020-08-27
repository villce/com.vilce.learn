package com.vilce.common.model.po;

import com.vilce.common.model.enums.ResultStatus;

import java.io.Serializable;

/**
 * @Description: 基础返回类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.po.BaseResponse
 * @Author: 雷才哲
 * @Date: 2020/8/26 18:09
 * @Version: 1.0
 */
public class BaseResponse<T> implements Serializable {
    private int status;
    private String message;
    private T data;

    public static <T> BaseResponse<T> buildResponse(int status, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(status);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static <T> BaseResponse<T> buildResponse(ResultStatus resultStatus) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(resultStatus.getStatus());
        baseResponse.setMessage(resultStatus.getMessage());
        return baseResponse;
    }

    public static <T> BaseResponse<T> buildResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(ResultStatus.SUCCESS.getStatus());
        baseResponse.setMessage(ResultStatus.SUCCESS.getMessage());
        return baseResponse;
    }

    public static <T> BaseResponse<T> buildResponse(T data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(ResultStatus.SUCCESS.getStatus());
        baseResponse.setMessage(ResultStatus.SUCCESS.getMessage());
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> buildResponse(int status, String message, T data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(status);
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> buildResponse(ResultStatus resultStatus, T data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(resultStatus.getStatus());
        baseResponse.setMessage(resultStatus.getMessage());
        baseResponse.setData(data);
        return baseResponse;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
