package com.vilce.common.autoconfig.returnvalue.handler;

import com.vilce.common.autoconfig.returnvalue.annotation.ApiWrapperIgnore;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.common.utils.RouteUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Description: HttpEntity返回值控制器
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.handler.ResponseHttpEntityMethodReturnValueHandler
 * @Author: 雷才哲
 * @Date: 2019/11/13 17:03
 * @Version: 1.0
 */
public class ResponseHttpEntityMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler proxyObject;

    public ResponseHttpEntityMethodReturnValueHandler(HandlerMethodReturnValueHandler handler) {
        this.proxyObject = handler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (HttpEntity.class.isAssignableFrom(returnType.getParameterType()) &&
                !RequestEntity.class.isAssignableFrom(returnType.getParameterType()));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //标注该请求已经在当前处理程序处理过
        mavContainer.setRequestHandled(true);
        ResponseEntity entity = (ResponseEntity) returnValue;
        //获取ResponseEntity封装的真实返回值
        Object body = (null == returnValue) ? null : entity.getBody();
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (entity.getStatusCode().value() == ResultStatus.NOT_FOUND.getStatus()) {
            String path = ((Map) body).get("path").toString();
            BaseResponse baseResponse = BaseResponse.buildResponse(ResultStatus.NOT_FOUND.getStatus(), StringUtils.join("接口【", path, "】不存在"));
            proxyObject.handleReturnValue(ResponseEntity.ok(baseResponse), returnType, mavContainer, webRequest);
        } else if (RouteUtils.match(request.getRequestURI())
                || returnType.hasMethodAnnotation(ApiWrapperIgnore.class)
                || returnType.getContainingClass().isAnnotationPresent(ApiWrapperIgnore.class)) {
            proxyObject.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else if (null != body && (body instanceof BaseResponse)) {
            proxyObject.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else {
            Type type = returnType.getMethod().getGenericReturnType();
            /**
             * 1.如果返回的是ResponseEntity类，无泛型化参数
             * 2.返回的ResponseEntity带泛型化参数，且参数是void
             */
            if ((type.equals(ResponseEntity.class)) || ((type instanceof ParameterizedType) && (((ParameterizedType) type).getActualTypeArguments()[0]).equals(Void.class))) {
                BaseResponse baseResponse = BaseResponse.buildResponse();
                proxyObject.handleReturnValue(ResponseEntity.ok(baseResponse), returnType, mavContainer, webRequest);
            } else {
                BaseResponse baseResponse = BaseResponse.buildResponse();
                baseResponse.setData(body);
                proxyObject.handleReturnValue(ResponseEntity.ok(baseResponse), returnType, mavContainer, webRequest);
            }
        }
    }
}
