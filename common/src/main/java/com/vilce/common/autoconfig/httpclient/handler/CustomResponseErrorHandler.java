package com.vilce.common.autoconfig.httpclient.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @Description: 自定义异常处理
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.httpclient.handler.CustomResponseErrorHandler
 * @Author: 雷才哲
 * @Date: 2020/11/12 16:08
 * @Version: 1.0
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {
    /**
     * 判定响应是否有任何错误
     * true :返回的响应有错误，false无错误
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    }
}
