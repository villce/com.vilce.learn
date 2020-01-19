#添加Java启动的必要镜像
FROM openjdk:8u181-jre-alpine3.8 as environment
#设置开放端口号
EXPOSE 8006
#创建一个目录存放jar包和配置
RUN mkdir -p /lcz/application/springbootVue
#添加jar包
ADD springboot_vue.jar /lcz/application/springbootVue/springboot_vue.jar
#添加进入docker容器后的目录
WORKDIR /lcz/application/springbootVue
#修改文件的创建修改时间
RUN bash -c 'touch /lcz/application/springbootVue/springboot_vue.jar'
#启动容器执行命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/lcz/application/springbootVue/springboot_vue.jar"]
#设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone