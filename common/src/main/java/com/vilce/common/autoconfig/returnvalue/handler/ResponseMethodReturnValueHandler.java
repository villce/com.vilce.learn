package com.vilce.common.autoconfig.returnvalue.handler;

import com.vilce.common.autoconfig.returnvalue.annotation.ApiWrapperIgnore;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.common.utils.RouteUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 控制器返回返回值包装类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.handler.ResponseMethodReturnValueHandler
 * @Author: 雷才哲
 * @Date: 2019/11/13 16:47
 * @Version: 1.0
 */
public class ResponseMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler proxyObject;

    public ResponseMethodReturnValueHandler(HandlerMethodReturnValueHandler handler) {
        this.proxyObject = handler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        boolean result = AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ResponseBody.class) ||
                methodParameter.hasMethodAnnotation(ResponseBody.class);
        return result;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //标注该请求已经在当前处理程序处理过
        mavContainer.setRequestHandled(true);
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (RouteUtils.match(request.getRequestURI())
                || returnType.hasMethodAnnotation(ApiWrapperIgnore.class)
                || returnType.getContainingClass().isAnnotationPresent(ApiWrapperIgnore.class)) {
            proxyObject.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else if (null != returnValue && (returnValue instanceof BaseResponse)) {
            proxyObject.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else {
            if (returnType.getMethod().getReturnType().equals(Void.TYPE)) {
                BaseResponse baseResponse = BaseResponse.buildResponse();
                proxyObject.handleReturnValue(baseResponse, returnType, mavContainer, webRequest);
            } else {
                BaseResponse baseResponse = BaseResponse.buildResponse();
                baseResponse.setData(returnValue);
                proxyObject.handleReturnValue(baseResponse, returnType, mavContainer, webRequest);
            }

        }
    }
}
