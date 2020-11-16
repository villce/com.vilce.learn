package com.vilce.common.autoconfig.converter;

import com.vilce.common.model.log.utils.LoggerUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @Description: 响应报文Content-Type编码UTF-8（默认开启）
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.converter.VilceMappingJackson2HttpMessageConverterAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/12 15:11
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties(Jackson2MessagesProperties.class)
@ConditionalOnProperty(prefix = "spring.vilce.jackson", name = "enable", havingValue = "true", matchIfMissing = true)
public class VilceMappingJackson2HttpMessageConverterAutoConfiguration implements CommandLineRunner {
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    public VilceMappingJackson2HttpMessageConverterAutoConfiguration(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    @PostConstruct
    public void SmallGrainMappingJackson2HttpMessageConverterAutoConfiguration() {
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.info(VilceMappingJackson2HttpMessageConverterAutoConfiguration.class, "【自动化配置】---响应报文Content-Type编码组件初始化完成...");
    }

}
