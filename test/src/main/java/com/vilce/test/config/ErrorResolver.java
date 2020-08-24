package com.vilce.test.config;

import com.vilce.common.exception.ServiceCustomException;
import com.vilce.common.utils.AjaxUtil;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.LoggerUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.config.ErrorResolver
 * @Author: 雷才哲
 * @Date: 2020/8/21 16:26
 * @Version: 1.0
 */
@Component
public class ErrorResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String errorMsg = "";
            boolean isAjax = "1".equals(request.getParameter("isAjax"));

            //ex 为业务层抛出的自定义异常
            if (e instanceof ServiceCustomException) {
                ServiceCustomException customEx = (ServiceCustomException) e;
                errorMsg = "customStatus:" + customEx.getCustomStatus() + ",customMessage:" + customEx.getCustomMessage()
                        + "\r\n" + ExceptionUtils.getStackTrace(customEx);
                LoggerUtils.error(ErrorResolver.class, errorMsg);
            } else {
                //ex为非自定义异常，则
                errorMsg = ExceptionUtils.getStackTrace(e);
                LoggerUtils.error(ErrorResolver.class, errorMsg);
            }
            if (isAjax) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSONUtils.toJson(AjaxUtil.messageMap(500, errorMsg)));
                return new ModelAndView();
            } else {
                //否则，  输出错误信息到自定义的500.jsp页面
                ModelAndView mv = new ModelAndView("/error/500.jsp");
                mv.addObject("errorMsg", errorMsg);
                return mv;
            }
        } catch (IOException ex) {
            LoggerUtils.error(ErrorResolver.class, ExceptionUtils.getStackTrace(ex));
        }
        return new ModelAndView();
    }
}
