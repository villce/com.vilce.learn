package com.vilce.common.config.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Description: HttpHeader类型返回值处理程序
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.handler.ResponseHttpHeadersReturnValueHandler
 * @Author: 雷才哲
 * @Date: 2019/11/13 16:58
 * @Version: 1.0
 */
public class ResponseHttpHeadersReturnValueHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler proxyObject;

    public ResponseHttpHeadersReturnValueHandler(HandlerMethodReturnValueHandler handler){
        this.proxyObject = handler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return HttpHeaders.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        if (o instanceof HttpHeaders){
            HttpHeaders httpHeaders = (HttpHeaders) o;
            proxyObject.handleReturnValue(httpHeaders,methodParameter,modelAndViewContainer,nativeWebRequest);
        }
    }
}
