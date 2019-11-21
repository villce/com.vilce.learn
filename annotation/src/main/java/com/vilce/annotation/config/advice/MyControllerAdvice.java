package com.vilce.annotation.config.advice;

import com.vilce.annotation.model.BaseResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: controller 增强器
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.config.advice.ControllerHandlers
 * @Author: 雷才哲
 * @Date: 2019/11/13 13:47
 * @Version: 1.0
 */
//忽略所有警示
@SuppressWarnings("all")
@RestControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute
    public void fillData(Model model){
        model.addAttribute("vilce","测试");
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse errorHandler(RuntimeException e){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(-1);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length>0){
            StackTraceElement element = elements[0];
            baseResponse.setMessage("类："+element.getClass()+"方法："+element.getMethodName()+"第"+element.getLineNumber()+"行");
        }
        return baseResponse;
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        BaseResponse result = new BaseResponse();
        result.setStatus(-1);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            result.setMessage("类："+element.getClass()+"方法："+element.getMethodName()+"第"+element.getLineNumber()+"行");
        }
        return result;
    }
}
