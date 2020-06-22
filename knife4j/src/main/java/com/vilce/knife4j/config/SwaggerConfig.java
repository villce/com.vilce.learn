package com.vilce.knife4j.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.knife4j.config.SwaggerConfig
 * @Author: 雷才哲
 * @Date: 2020/6/22 10:44
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig implements WebMvcConfigurer {
    @Value(value = "${swagger.enabled}")
    boolean swaggerEnabled;

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vilce.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .description("knife4j测试")
                .contact(new Contact("lcz", "http://localhost/com.vilce.api.git", "test@qq.com"))
                .version("v1.0.0")
                .title("API文档")
                .license("Apache2.0")
                .licenseUrl("http://www,apache.org/licenses/LICENSE-2.0")
                .build();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
