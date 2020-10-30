package com.vilce.springboot_vue.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
 * @Package: com.vilce.springboot_vue.config.swagger.SwaggerConfiguration
 * @Author: 雷才哲
 * @Date: 2019/12/19 20:02
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.vilce.springboot_vue"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .description("Vue测试项目接口")
                .contact(new Contact("雷才哲", "https://github.com", "yunyu@eastmoney.com"))
                .version("v1.2.0")
                .title("API文档")
                .license("Apache2.0")
                .licenseUrl("http://www,apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
