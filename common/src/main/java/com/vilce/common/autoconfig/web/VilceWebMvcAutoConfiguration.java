package com.vilce.common.autoconfig.web;

import com.vilce.common.autoconfig.web.annotation.ApiPrefix;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

/**
 * @Description: WebMvc自动化配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.web.VilceWebMvcAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/12 19:23
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties(WebProperties.class)
public class VilceWebMvcAutoConfiguration implements WebMvcConfigurer, CommandLineRunner {

    private WebProperties webProperties;
    /**
     * 自定义路由规则是否已加载
     */
    private boolean enablePathMatch;
    /**
     * 自定义跨域规则是否已加载
     */
    private boolean enableCors;
    /**
     * 忽略URL前缀的控制器类
     */
    private static String[] ignoreUrlPrefixController = new String[]{"springfox.documentation.swagger.web.ApiResourceController",
            "org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController",
            "springfox.documentation.swagger2.web.Swagger2ControllerWebMvc"};

    public VilceWebMvcAutoConfiguration(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    /**
     * 配置路由规则
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        //区分大小写,默认true
        matcher.setCaseSensitive(webProperties.getPath().isCaseSensitive());
        //是否去除前后空格,默认false
        matcher.setTrimTokens(webProperties.getPath().isTrimTokens());
        //分隔符
        matcher.setPathSeparator("/");
        //是否缓存匹配规则,默认null等于true
        matcher.setCachePatterns(webProperties.getPath().isCachePatterns());
        //设置路由匹配规则
        configurer.setPathMatcher(matcher);
        //设置URL末尾是否支持斜杠，默认true,如/a/b/有效，/a/b也有效
        configurer.setUseTrailingSlashMatch(webProperties.getPath().isUseTrailingSlashMatch());
        //忽略URL前缀的控制器类
        ignoreUrlPrefixController = ArrayUtils.addAll(ignoreUrlPrefixController, StringUtils.split(webProperties.getPath().getIgnoreControllerUrlPrefix(), ","));
        //给所有的接口统一添加前缀
        configurer.addPathPrefix(webProperties.getPath().getPrefix(), c -> {
            /**
             * 1.注解@ApiPrefix优先级最高
             * 2.isEnableAllPrefix方法优先级第二
             */
            if (c.isAnnotationPresent(ApiPrefix.class)) {
                if (BooleanUtils.isFalse(c.getAnnotation(ApiPrefix.class).ignore())) {
                    return true;
                } else {
                    return false;
                }
            }
            if (!ArrayUtils.contains(ignoreUrlPrefixController, c.getName()) && (BooleanUtils.isTrue(webProperties.getPath().isEnableAllPrefix()))) {
                return true;
            } else {
                return false;
            }
        });
        enablePathMatch = true;
    }

    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (BooleanUtils.isFalse(webProperties.getCors().isEnable())) {
            return;
        }
        //启用跨域匹配的路径，默认所有请求，示例：/admin或/admin/**
        CorsRegistration registration = registry.addMapping("/**");
        //允许来自所有域名请求
        if (ArrayUtils.isNotEmpty(webProperties.getCors().getAllowedOrigins())) {
            registration.allowedOrigins(webProperties.getCors().getAllowedOrigins());
        } else {
            registration.allowedOrigins("*");
        }
        //设置所允许的HTTP请求方法，*号代表允许所有方法
        if (ArrayUtils.isNotEmpty(webProperties.getCors().getAllowedMethods())) {
            registration.allowedMethods(webProperties.getCors().getAllowedMethods());
        } else {
            registration.allowedMethods("OPTIONS", "GET", "PUT", "POST");
        }
        //服务器支持的所有头信息字段，多个字段用逗号分隔；默认支持所有，*号代表所有
        if (ArrayUtils.isNotEmpty(webProperties.getCors().getAllowedHeaders())) {
            registration.allowedHeaders(webProperties.getCors().getAllowedHeaders());
        } else {
            registration.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
        }
        //浏览器是否应该发送凭据，如是否允许发送Cookie，true为允许
        if (BooleanUtils.isFalse(webProperties.getCors().isAllowCredentials())) {
            registration.allowCredentials(false);
        } else {
            registration.allowCredentials(true);
        }
        //设置响应HEAD,默认无任何设置，不可以使用*号
        if (ArrayUtils.isNotEmpty(webProperties.getCors().getExposedHeaders())) {
            registration.exposedHeaders(webProperties.getCors().getExposedHeaders());
        }
        //设置多长时间内不需要发送预检验请求，可以缓存该结果，默认1800秒
        if (Objects.nonNull(webProperties.getCors().getMaxAge())) {
            registration.maxAge(webProperties.getCors().getMaxAge());
        }
        enableCors = true;
    }

    @Override
    public void run(String... args) throws Exception {
        if (enablePathMatch) {
            LoggerUtils.info(VilceWebMvcAutoConfiguration.class, "【自动化配置】---API前缀组件初始化完成...");
        }
        if (enableCors) {
            LoggerUtils.info(VilceWebMvcAutoConfiguration.class, "【自动化配置】---跨域组件初始化完成...");
        }
    }
}
