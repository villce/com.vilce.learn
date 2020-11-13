#添加Java启动的必要镜像
FROM mydockerhub.com:5000/jre:1.1
#添加jar包
COPY springboot_vue.jar /lcz/application/springbootVue/springboot_vue.jar
#添加进入docker容器后的目录
WORKDIR /lcz/application/springbootVue
#启动容器执行命令
ENTRYPOINT ["java","-jar","springboot_vue.jar"]
