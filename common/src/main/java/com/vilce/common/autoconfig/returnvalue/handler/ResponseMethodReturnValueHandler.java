package com.vilce.common.autoconfig.returnvalue.handler;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.common.utils.SwaggerUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
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
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        if (SwaggerUtils.URLS.contains(request.getRequestURI())) {
            // 包含在路径配置文件里或添加有标签注解的，都不进行处理
            proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else if (null != o && (o instanceof BaseResponse)) {
            // entity不为空但继承BaseResponse，不进行处理
            proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else {
            BaseResponse baseResponse = BaseResponse.buildResponse();
            if (!methodParameter.getMethod().getReturnType().equals(Void.TYPE)) {
                baseResponse.setData(o);
            }
            proxyObject.handleReturnValue(baseResponse, methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }
}
