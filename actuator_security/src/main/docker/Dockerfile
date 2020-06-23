#添加Java启动的必要镜像
FROM openjdk:8u181-jre-alpine3.8 as environment
#创建一个目录存放jar包和配置
RUN mkdir -p /lcz/application/actuator
#添加jar包
ADD actuator_security.jar /lcz/application/actuator/actuator_security.jar
#添加进入docker容器后的目录
WORKDIR /lcz/application/actuator
#启动容器执行命令
ENTRYPOINT ["java","-jar","/lcz/application/actuator/actuator_security.jar"]