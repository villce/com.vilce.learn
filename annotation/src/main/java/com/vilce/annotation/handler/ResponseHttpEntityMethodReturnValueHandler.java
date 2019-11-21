package com.vilce.annotation.handler;

import com.vilce.annotation.config.swagger.SwaggerConfig;
import com.vilce.annotation.model.BaseResponse;
import com.vilce.annotation.utils.SwaggerUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.handler.ResponseHttpEntityMethodReturnValueHandler
 * @Author: 雷才哲
 * @Date: 2019/11/13 17:03
 * @Version: 1.0
 */
public class ResponseHttpEntityMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler proxyObject;

    public ResponseHttpEntityMethodReturnValueHandler(HandlerMethodReturnValueHandler handler){
        this.proxyObject = handler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        boolean result =  HttpEntity.class.isAssignableFrom(methodParameter.getParameterType()) &&
                !RequestEntity.class.isAssignableFrom(methodParameter.getParameterType());
        return result;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        Object entity = (null == o)?null:((ResponseEntity) o).getBody();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        if(SwaggerUtils.URLS.contains(request.getRequestURI())){
            proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else if(null != entity && (entity instanceof BaseResponse)){
            proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else {
            Map<String, Object> resultMap = new LinkedHashMap<>();
            resultMap.put("status", 0);
            resultMap.put("message", "访问成功");
            resultMap.put("data", entity);
            proxyObject.handleReturnValue(ResponseEntity.ok(resultMap), methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }
}
