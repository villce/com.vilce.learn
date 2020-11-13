package com.vilce.common.autoconfig.httpclient.config;

import com.vilce.common.autoconfig.httpclient.handler.CustomResponseErrorHandler;
import com.vilce.common.autoconfig.httpclient.interceptor.HttpClientInterceptor;
import com.vilce.common.autoconfig.httpclient.service.AsyncLogHttpClientService;
import com.vilce.common.autoconfig.httpclient.service.impl.AsyncLogHttpClientServiceImpl;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @Description: httpClient自动化配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.httpclient.config.HttpClientAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/6 18:01
 * @Version: 1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HttpClientProperties.class)
@ConditionalOnProperty(prefix = "spring.vilce.http-client", name = "enable", havingValue = "true", matchIfMissing = true)
@Import(value = {AsyncLogHttpClientServiceImpl.class})
public class HttpClientAutoConfiguration implements CommandLineRunner {

    /**
     * 读取配置属性服务类
     */
    private HttpClientProperties httpClientProperties;
    private AsyncLogHttpClientService asyncLogHttpClientService;

    public HttpClientAutoConfiguration(HttpClientProperties httpClientProperties, AsyncLogHttpClientService asyncLogHttpClientService){
        this.httpClientProperties = httpClientProperties;
        this.asyncLogHttpClientService = asyncLogHttpClientService;
    }
    /**
     * 将RestTemplate加入容器
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory){
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        //设置BufferingClientHttpRequestFactory将输入流和输出流保存到内存中，允许多次读取
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory));
        //设置自定义异常处理
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
        if(httpClientProperties.isEnableInterceptor()){
            //添加拦截器
            restTemplate.setInterceptors(Collections.singletonList(new HttpClientInterceptor(asyncLogHttpClientService)));
        }
        return restTemplate;
    }

    /**
     * 定义HTTP请求工厂方法
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //读取超时5秒,默认无限限制
        factory.setReadTimeout(httpClientProperties.getReadTimeOut());
        //连接超时10秒，默认无限制
        factory.setConnectTimeout(httpClientProperties.getConnectTimeOut());
        return factory;
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.info(HttpClientAutoConfiguration.class, "【自动化配置】---RestTemplate(HttpClient)组件初始化完成...");
    }
}
