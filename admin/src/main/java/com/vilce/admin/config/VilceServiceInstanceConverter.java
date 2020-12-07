package com.vilce.admin.config;

import java.net.URI;
import java.util.Map;

import de.codecentric.boot.admin.server.cloud.discovery.ServiceInstanceConverter;
import de.codecentric.boot.admin.server.domain.values.Registration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.util.UriComponentsBuilder;

import static java.util.Collections.emptyMap;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.admin.config.VilceServiceInstanceConverter
 * @Author: 雷才哲
 * @Date: 2020/12/7 17:09
 * @Version: 1.0
 */
public class VilceServiceInstanceConverter implements ServiceInstanceConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(VilceServiceInstanceConverter.class);

    private static final String KEY_MANAGEMENT_SCHEME = "management.scheme";

    private static final String KEY_MANAGEMENT_ADDRESS = "management.address";

    private static final String KEY_MANAGEMENT_PORT = "management.port";

    private static final String KEY_MANAGEMENT_PATH = "management.context-path";

    private static final String KEY_HEALTH_PATH = "health.path";

    /**
     * Default context-path to be appended to the url of the discovered service for the
     * managment-url.
     */
    private String managementContextPath = "/actuator";

    /**
     * Default path of the health-endpoint to be used for the health-url of the discovered
     * service.
     */
    private String healthEndpointPath = "health";

    @Override
    public Registration convert(ServiceInstance instance) {
        LOGGER.debug("Converting service '{}' running at '{}' with metadata {}", instance.getServiceId(),
                instance.getUri(), instance.getMetadata());
        Registration.Builder builder = Registration.create(instance.getServiceId(), getHealthUrl(instance).toString());
        String serviceUrl = getSwagger(instance);
        Registration registration = builder.managementUrl(getManagementUrl(instance).toString()).serviceUrl(serviceUrl)
                .metadata(getMetadata(instance)).build();
        return registration;
    }

    protected String getSwagger(ServiceInstance instance) {
        String swaggerScheme = this.getManagementScheme(instance);
        String swaggerHost = this.getManagementHost(instance);
        String swaggerPort = StringUtils.join(instance.getMetadata().get("swaggerPort"), instance.getMetadata().get("swaggerUri"));
        String swaggerUrl = StringUtils.join(swaggerScheme, "://", swaggerHost, ":", swaggerPort);
        return swaggerUrl;
    }

    protected URI getHealthUrl(ServiceInstance instance) {
        return UriComponentsBuilder.fromUri(getManagementUrl(instance)).path("/").path(getHealthPath(instance)).build()
                .toUri();
    }

    protected String getHealthPath(ServiceInstance instance) {
        String healthPath = instance.getMetadata().get(KEY_HEALTH_PATH);
        if (!isEmpty(healthPath)) {
            return healthPath;
        }
        return this.healthEndpointPath;
    }

    protected URI getManagementUrl(ServiceInstance instance) {
        URI serviceUrl = this.getServiceUrl(instance);
        String managementScheme = this.getManagementScheme(instance);
        String managementHost = this.getManagementHost(instance);
        int managementPort = this.getManagementPort(instance);

        UriComponentsBuilder builder;
        if (serviceUrl.getHost().equals(managementHost) && serviceUrl.getScheme().equals(managementScheme)
                && serviceUrl.getPort() == managementPort) {
            builder = UriComponentsBuilder.fromUri(serviceUrl);
        }
        else {
            builder = UriComponentsBuilder.newInstance().scheme(managementScheme).host(managementHost);
            if (managementPort != -1) {
                builder.port(managementPort);
            }
        }

        return builder.path("/").path(getManagementPath(instance)).build().toUri();
    }

    private String getManagementScheme(ServiceInstance instance) {
        String managementServerScheme = instance.getMetadata().get(KEY_MANAGEMENT_SCHEME);
        if (!isEmpty(managementServerScheme)) {
            return managementServerScheme;
        }
        return getServiceUrl(instance).getScheme();
    }

    protected String getManagementHost(ServiceInstance instance) {
        String managementServerHost = instance.getMetadata().get(KEY_MANAGEMENT_ADDRESS);
        if (!isEmpty(managementServerHost)) {
            return managementServerHost;
        }
        return getServiceUrl(instance).getHost();
    }

    protected int getManagementPort(ServiceInstance instance) {
        String managementPort = instance.getMetadata().get(KEY_MANAGEMENT_PORT);
        if (!isEmpty(managementPort)) {
            return Integer.parseInt(managementPort);
        }
        return getServiceUrl(instance).getPort();
    }

    protected String getManagementPath(ServiceInstance instance) {
        String managementPath = instance.getMetadata().get(VilceServiceInstanceConverter.KEY_MANAGEMENT_PATH);
        if (!isEmpty(managementPath)) {
            return managementPath;
        }
        return this.managementContextPath;
    }

    protected URI getServiceUrl(ServiceInstance instance) {
        return instance.getUri();
    }

    protected Map<String, String> getMetadata(ServiceInstance instance) {
        return (instance.getMetadata() != null) ? instance.getMetadata() : emptyMap();
    }

    public void setManagementContextPath(String managementContextPath) {
        this.managementContextPath = managementContextPath;
    }

    public String getManagementContextPath() {
        return this.managementContextPath;
    }

    public void setHealthEndpointPath(String healthEndpointPath) {
        this.healthEndpointPath = healthEndpointPath;
    }

    public String getHealthEndpointPath() {
        return this.healthEndpointPath;
    }

}

