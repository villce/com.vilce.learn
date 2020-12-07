package com.vilce.admin.config;

import de.codecentric.boot.admin.server.cloud.config.AdminServerDiscoveryAutoConfiguration;
import de.codecentric.boot.admin.server.cloud.discovery.InstanceDiscoveryListener;
import de.codecentric.boot.admin.server.config.AdminServerAutoConfiguration;
import de.codecentric.boot.admin.server.config.AdminServerMarkerConfiguration;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.services.InstanceRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.admin.config.VilceServerDiscoveryAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/12/7 17:12
 * @Version: 1.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnSingleCandidate(DiscoveryClient.class)
@ConditionalOnBean(AdminServerMarkerConfiguration.Marker.class)
@ConditionalOnProperty(prefix = "spring.boot.admin.discovery", name = "enabled", matchIfMissing = true)
@AutoConfigureAfter(value = AdminServerAutoConfiguration.class,
        name = {"org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration",
                "org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClientAutoConfiguration"})
@AutoConfigureBefore(AdminServerDiscoveryAutoConfiguration.class)
@Import({VilceServiceInstanceConverter.class})
public class VilceServerDiscoveryAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.boot.admin.discovery")
    @Primary
    public InstanceDiscoveryListener instanceDiscoveryListener(VilceServiceInstanceConverter serviceInstanceConverter,
                                                               DiscoveryClient discoveryClient, InstanceRegistry registry, InstanceRepository repository) {
        InstanceDiscoveryListener listener = new InstanceDiscoveryListener(discoveryClient, registry, repository);
        listener.setConverter(serviceInstanceConverter);
        return listener;
    }
}
