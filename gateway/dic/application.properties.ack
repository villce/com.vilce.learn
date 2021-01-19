# 服务端口
server.port=8009

# log 级别
logging.level.org.springframework.cloud.gateway=debug

# 指定配置
# route_simple：简单尝试
# route_stripPrefix：截取请求
# route_uri：转发指定地址并传入参数
# route_addRequestParameter：转发指定服务并传入参数
# route_hystrix：熔断
# route_requestRateLimiter：限流
# route_all：综合
spring.profiles.active=route_all

# 应用名称
spring.application.name=gateway-master
# 是否和服务注册与发现组件结合，设置为 true 后可以直接使用应用名称调用服务
spring.cloud.gateway.discovery.locator.enabled=true
# 路由（routes：路由，它由唯一标识（ID）、目标服务地址（uri）、一组断言（predicates）和一组过滤器组成（filters）。filters 不是必需参数。）
# 路由标识（id：标识，具有唯一性）   简单尝试
spring.cloud.gateway.routes[0].id=route_all
# 目标服务地址（uri：地址，请求转发后的地址）
spring.cloud.gateway.routes[1].uri=lb://Vilce-Consul-Test
# 路由条件（predicates：断言，匹配 HTTP 请求内容）
# 转发地址格式为 uri/archive
spring.cloud.gateway.routes[2].predicates=Path=/api/routeAll

# 服务发现配置中心配置
# 服务IP地址
spring.cloud.consul.host=127.0.0.1
# 端口号
spring.cloud.consul.port=8500
# 启用服务发现，默认：true
spring.cloud.consul.discovery.enabled=true
# 启用服务注册，默认：true
spring.cloud.consul.discovery.register=false
# 服务发现tag，配置认证用户名和密码
#spring.cloud.consul.discovery.tags=user.name=admin,user.password=admin
# ACL访问令牌
spring.cloud.consul.discovery.acl-token=05435c6d-ae01-f534-5bb1-e4cbc1eaeb1f
