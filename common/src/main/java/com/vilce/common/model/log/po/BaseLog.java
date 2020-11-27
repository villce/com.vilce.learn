package com.vilce.common.model.log.po;

import java.util.Map;

/**
 * @Description: 日志基类
 * @ProjectName: com.eastmoney.emis.utils
 * @Package: com.eastmoney.emis.utils.common.po.BaseLog
 * @Author: 雷才哲
 * @Date: 2020/11/27 11:21
 * @Version: 1.0
 */
public class BaseLog {
    /**
     * 请求唯一编号
     */
    private String tId;
    /**
     * 请求URL
     */
    private String requestUrl;
    /**
     * 请求Method
     */
    private String method;
    /**
     * 协议
     */
    private String protocol;
    /**
     * 请求类型
     */
    private String contentType;
    /**
     * 请求参数
     */
    private Map<String, Object> requestParams;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }
}
