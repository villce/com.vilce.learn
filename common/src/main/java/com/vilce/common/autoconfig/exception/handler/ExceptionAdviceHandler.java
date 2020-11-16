package com.vilce.common.autoconfig.exception.handler;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Description: 异常捕获增强器
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.config.advice.ControllerHandlers
 * @Author: 雷才哲
 * @Date: 2019/11/13 13:47
 * @Version: 1.0
 */
//忽略所有警示
@SuppressWarnings("all")
public class ExceptionAdviceHandler {

    /**
     * 未知异常
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse unKnowExceptionHandler(Exception e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.ERROR);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "类的第", element.getLineNumber(), "行发生", e.toString(), "异常");
            result.setMessage(message);
        }
        return result;
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.RUNTIME_EXCEPTION);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "类的第",
                    element.getLineNumber(), "行发生", e.toString(), "异常");
            result.setMessage(message);
        }
        return result;
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointerExceptionHandler(NullPointerException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.NULL_POINTER_EXCEPTION);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "类的第",
                    element.getLineNumber(), "行发生", e.toString(), "异常");
            result.setMessage(message);
        }
        return result;
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    public BaseResponse classCastExceptionHandler(ClassCastException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.CLASS_CAST_EXCEPTION);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "类的第",
                    element.getLineNumber(), "行发生", e.toString(), "异常");
            result.setMessage(message);
        }

        return result;
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    public BaseResponse iOExceptionHandler(IOException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.IO_EXCEPTION);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "类的第",
                    element.getLineNumber(), "行发生", e.toString(), "异常");
            result.setMessage(message);
        }

        return result;
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public BaseResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.INDEX_OUTOF_BOUNDS_EXCEPTION);
        printErrorMessage(e);
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "类的第",
                    element.getLineNumber(), "行发生", e.toString(), "异常");
            result.setMessage(message);
        }
        return result;
    }

    /**
     * 参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseResponse requestTypeMismatch(MethodArgumentTypeMismatchException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTIION);
        printErrorMessage(e);
        String message = StringUtils.join("参数类型不匹配，参数", e.getName(), "类型必须为", e.getRequiredType());
        result.setMessage(message);
        return result;
    }

    /**
     * 缺少参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse requestMissingServletRequest(MissingServletRequestParameterException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
        printErrorMessage(e);
        String message = StringUtils.join("缺少必要参数，参数名称为", e.getParameterName());
        result.setMessage(message);
        return result;
    }

    /**
     * 请求method不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse requestMissingServletRequest(HttpRequestMethodNotSupportedException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION);
        printErrorMessage(e);
        String message = StringUtils.join("不支持", e.getMethod(), "方法，支持", StringUtils.join(e.getSupportedMethods(),
                ","), "类型");
        result.setMessage(message);
        return result;
    }

    /**
     * 控制器方法中@RequestBody类型参数数据类型转换异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse httpMessageNotReadableException(HttpMessageNotReadableException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.REQUEST_BODY_MESSAGE);
        printErrorMessage(e);
        return result;
    }

    /**
     * 控制器方法参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.PARAM_EXCEPTION);
        printErrorMessage(e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String message = StringUtils.join(fieldError.getDefaultMessage());
        result.setMessage(message);
        return result;
    }

    /**
     * 控制器方法参数Validate异常
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse validModelBindException(BindException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.PARAM_EXCEPTION.getStatus(), e.getMessage());
        printErrorMessage(e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String message = StringUtils.join(fieldError.getDefaultMessage());
        result.setMessage(message);
        return result;
    }

    /**
     * 如果代理异常调用方法将会抛出此异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    public BaseResponse undeclaredThrowableException(UndeclaredThrowableException e) {
        BaseResponse result = BaseResponse.buildResponse(ResultStatus.SYSTEM_EXCEPTION);
        Throwable throwable = e.getCause().getCause();
        printErrorMessage(throwable);
        StackTraceElement[] elements = throwable.getStackTrace();
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            String message = StringUtils.join("类|方法", element.getClassName(), ".", element.getMethodName(), "的第",
                    element.getLineNumber(), "行发生", throwable.toString(), "异常");
        }
        return result;
    }

    /**
     * 如果抛出自定义异常
     */
    @ExceptionHandler(BasicException.class)
    public BaseResponse businessThrowableException(BasicException e) {
        printBusinessErrorMessage(e);
        return BaseResponse.buildResponse(e.getStatusCode(), e.getErrorMessage());
    }

    /**
     * 打印错误日志信息
     */
    private void printErrorMessage(Throwable e) {
        String message = e.toString();
        for (StackTraceElement element : e.getStackTrace()) {
            message = StringUtils.join(message, "\n", element.toString());
        }
        LoggerUtils.error(ExceptionAdviceHandler.class, message);
    }

    /**
     * 打印自定义错误日志信息
     */
    private void printBusinessErrorMessage(BasicException e) {
        LoggerUtils.error(ExceptionAdviceHandler.class, StringUtils.join(e, " 【statusCode】", e.getStatusCode(), ", 【errorMessage】", e.getErrorMessage()));
    }
}
