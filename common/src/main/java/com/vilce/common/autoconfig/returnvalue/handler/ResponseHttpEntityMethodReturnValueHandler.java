package com.vilce.common.autoconfig.returnvalue.handler;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.common.utils.SwaggerUtils;
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
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        //标注该请求已经在当前处理程序处理过
        modelAndViewContainer.setRequestHandled(true);
        //获取ResponseEntity封装的真实返回值
        Object entity = (null == o) ? null : ((ResponseEntity) o).getBody();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        if (SwaggerUtils.URLS.contains(request.getRequestURI())) {
            // 包含在路径配置文件里或添加有标签注解的，都不进行处理
            proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else if (null != entity && (entity instanceof BaseResponse)) {
            // entity不为空但继承BaseResponse，不进行处理
            proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else {
            BaseResponse baseResponse = BaseResponse.buildResponse();
            Type type = methodParameter.getMethod().getGenericReturnType();
            /**
             * 1.如果返回的是ResponseEntity类，无泛型化参数
             * 2.返回的ResponseEntity带泛型化参数，且参数是void
             */
            if (!(type.equals(ResponseEntity.class)) && !((type instanceof ParameterizedType) && (((ParameterizedType) type).getActualTypeArguments()[0]).equals(Void.class))) {
                baseResponse.setData(entity);
            }
            proxyObject.handleReturnValue(ResponseEntity.ok(baseResponse), methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }
}
