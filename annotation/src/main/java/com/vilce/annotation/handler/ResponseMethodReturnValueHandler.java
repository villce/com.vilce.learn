package com.vilce.annotation.handler;

import com.vilce.annotation.model.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.LinkedHashMap;
import java.util.Map;

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
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        //标注该请求已经在当前处理程序处理过
        modelAndViewContainer.setRequestHandled(true);
        if ((null == o) && (o instanceof BaseResponse)) {
            proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("status", 0);
            map.put("message", "访问成功");
            map.put("data", o);
            proxyObject.handleReturnValue(map, methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }
}
