package com.vilce.image.config;

import com.vilce.common.utils.LoggerUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.image.config.GlobalExceptionHandler
 * @Author: 雷才哲
 * @Date: 2019/12/9 15:28
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { IOException.class , RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(Exception exception, WebRequest request) {
        LoggerUtils.error(GlobalExceptionHandler.class,"Catch an exception");
        return  new ModelAndView("500");
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView noMapping(Exception exception, WebRequest request) {
        LoggerUtils.error(GlobalExceptionHandler.class,"No mapping exception");
        return  new ModelAndView("404");
    }

    @ExceptionHandler(value = {MultipartException.class})
    public ModelAndView fileError(Exception exception, WebRequest request){
        LoggerUtils.error(GlobalExceptionHandler.class,"上传文件异常，信息:{}");
        System.out.println(exception.getMessage());
        return  new ModelAndView("/error");
    }
}
