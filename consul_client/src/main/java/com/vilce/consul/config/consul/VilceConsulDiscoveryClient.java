//package com.vilce.consul.config.consul;
//
//import com.ecwid.consul.v1.ConsulClient;
//import com.ecwid.consul.v1.QueryParams;
//import com.ecwid.consul.v1.Response;
//import com.ecwid.consul.v1.catalog.CatalogServicesRequest;
//import com.ecwid.consul.v1.health.HealthServicesRequest;
//import com.ecwid.consul.v1.health.model.HealthService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.DefaultServiceInstance;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static org.springframework.cloud.consul.discovery.ConsulServerUtils.findHost;
//import static org.springframework.cloud.consul.discovery.ConsulServerUtils.getMetadata;
//
///**
// * @Description: 自定义服务发现
// * @ProjectName: com.vilce.learn
// * @Package: com.vilce.common.autoconfig.consul.discovery.VilceConsulDiscoveryClient
// * @Author: 雷才哲
// * @Date: 2020/11/23 16:57
// * @Version: 1.0
// */
//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class VilceConsulDiscoveryClient implements DiscoveryClient {
//    private final ConsulClient client;
//    private final ConsulDiscoveryProperties properties;
//
//    @Value("${spring.vilce.env}")
//    private String env;
//
//    public VilceConsulDiscoveryClient(ConsulClient client, ConsulDiscoveryProperties properties) {
//        this.client = client;
//        this.properties = properties;
//    }
//
//
//    @Override
//    public String description() {
//        return "Spring Cloud Consul Discovery Client";
//    }
//
//    @Override
//    public List<ServiceInstance> getInstances(final String serviceId) {
//        return getInstances(serviceId,
//                new QueryParams(this.properties.getConsistencyMode()));
//    }
//
//    public List<ServiceInstance> getInstances(final String serviceId,
//                                              final QueryParams queryParams) {
//        List<ServiceInstance> instances = new ArrayList<>();
//
//        addInstancesToList(instances, serviceId, queryParams);
//
//        return instances;
//    }
//
//    private void addInstancesToList(List<ServiceInstance> instances, String serviceId,
//                                    QueryParams queryParams) {
//
//        HealthServicesRequest request = HealthServicesRequest.newBuilder()
//                .setTag(this.properties.getDefaultQueryTag())
//                .setPassing(this.properties.isQueryPassing()).setQueryParams(queryParams)
//                .setToken(this.properties.getAclToken()).build();
//        Response<List<HealthService>> services = this.client.getHealthServices(serviceId,
//                request);
//
//        for (HealthService service : services.getValue()) {
//            String host = findHost(service);
//            Map<String, String> metadata = getMetadata(service,
//                    this.properties.isTagsAsMetadata());
//            boolean secure = false;
//            if (metadata.containsKey("secure")) {
//                secure = Boolean.parseBoolean(metadata.get("secure"));
//            }
//            String tag = metadata.keySet().iterator().next();
//            if (StringUtils.endsWithIgnoreCase(tag, env)) {
//                instances.add(new DefaultServiceInstance(service.getService().getId(),
//                        serviceId, host, service.getService().getPort(), secure, metadata));
//            }
//        }
//    }
//
//    public List<ServiceInstance> getAllInstances() {
//        List<ServiceInstance> instances = new ArrayList<>();
//
//        Response<Map<String, List<String>>> services = this.client
//                .getCatalogServices(CatalogServicesRequest.newBuilder()
//                        .setQueryParams(QueryParams.DEFAULT).build());
//        for (String serviceId : services.getValue().keySet()) {
//            addInstancesToList(instances, serviceId, QueryParams.DEFAULT);
//        }
//        return instances;
//    }
//
//    @Override
//    public List<String> getServices() {
//        String aclToken = this.properties.getAclToken();
//
//        CatalogServicesRequest request = CatalogServicesRequest.newBuilder()
//                .setQueryParams(QueryParams.DEFAULT)
//                .setToken(this.properties.getAclToken()).build();
//        return new ArrayList<>(
//                this.client.getCatalogServices(request).getValue().keySet());
//    }
//
//    @Override
//    public int getOrder() {
//        return this.properties.getOrder();
//    }
//
//    /**
//     * Depreacted local resolver.
//     */
//    @Deprecated
//    public interface LocalResolver {
//
//        String getInstanceId();
//
//        Integer getPort();
//
//    }
//}
