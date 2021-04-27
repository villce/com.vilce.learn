# 设置基础镜像
FROM nginx
# 定义作者
MAINTAINER vilce <vilce@163.com>
#添加我们自己的配置 nginx.conf 在下面
ADD nginx.conf /etc/nginx/nginx.conf
# 将dist文件中的内容复制到 /root/application/vuejs/ 这个目录下面
COPY dist/ /usr/share/nginx/html/
