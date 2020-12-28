package com.vilce.admin;

import com.vilce.admin.config.VilceServiceInstanceConverter;
import de.codecentric.boot.admin.server.cloud.discovery.InstanceDiscoveryListener;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.services.InstanceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.admin.AdminApplication
 * @Author: 雷才哲
 * @Date: 2020/11/30 13:21
 * @Version: 1.0
 */
@SpringBootApplication
@EnableAdminServer
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

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
