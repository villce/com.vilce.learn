package com.vilce.common.autoconfig.returnvalue.config;

import com.vilce.common.autoconfig.returnvalue.handler.ResponseHttpEntityMethodReturnValueHandler;
import com.vilce.common.autoconfig.returnvalue.handler.ResponseHttpHeadersReturnValueHandler;
import com.vilce.common.autoconfig.returnvalue.handler.ResponseMethodReturnValueHandler;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 返参包装自动化配置（默认开启）
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.returnvalue.config.ReturnValueAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/10 19:37
 * @Version: 1.0
 */
@Order(3)
@Configuration
@EnableConfigurationProperties(ReturnValueProperties.class)
@ConditionalOnProperty(prefix = "spring.vilce.returnvalue", name = "enable", havingValue = "true", matchIfMissing = true)
public class ReturnValueAutoConfiguration implements InitializingBean, CommandLineRunner {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> list = handlerAdapter.getReturnValueHandlers();
        if (null != list) {
            List<HandlerMethodReturnValueHandler> newList = new ArrayList<>();
            for (HandlerMethodReturnValueHandler valueHandler : list) {
                if (valueHandler instanceof RequestResponseBodyMethodProcessor) {
                    ResponseMethodReturnValueHandler proxy = new ResponseMethodReturnValueHandler(valueHandler);
                    newList.add(proxy);
                } else if (valueHandler instanceof HttpEntityMethodProcessor) {
                    ResponseHttpEntityMethodReturnValueHandler proxy = new ResponseHttpEntityMethodReturnValueHandler(valueHandler);
                    newList.add(proxy);
                } else if (valueHandler instanceof HttpHeadersReturnValueHandler) {
                    ResponseHttpHeadersReturnValueHandler proxy = new ResponseHttpHeadersReturnValueHandler(valueHandler);
                    newList.add(proxy);
                } else {
                    newList.add(valueHandler);
                }
            }
            handlerAdapter.setReturnValueHandlers(newList);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.info(ReturnValueAutoConfiguration.class, "【自动化配置】---返回值包装组件初始化完成...");
    }
}
