echo "输入vue版本号"
read tag
export VUE_TAG=$tag
# 检查是否存在正在运行的容器
springbootContainer=$(docker-compose ps | grep springboot_vue)
echo $springbootContainer
vueContainer=$(docker-compose ps | grep vuejs)
echo $vueContainer
if [ -n "$springbootContainer"  -o  -n "$vueContainer" ];
then
  echo "删除正在运行的容器"
    docker-compose down
else
  echo "没有正在运行的容器"
fi
# 检查是否存在相同版本的镜像
springImage=$(docker images -a | grep mydockerhub.com:5000/springboot_vue | grep $tag)
echo $springImage
if [ ! -n "$springImage" ];
then
  echo "springboot_vue镜像不存在，拉取镜像"
  docker-compose pull springboot_vue
  if [ $? -eq 0 ];
  then
    echo "springboot_vue镜像拉取成功"
  else
    echo "springboot_vue镜像拉取失败，不存在该版本镜像"
    echo "构建springboot_vue该版本镜像"
    docker-compose build springboot_vue
    echo "将springboot_vue镜像上传私库"
    docker-compose push springboot_vue
  fi
else
  echo "存在springboot_vue镜像，可以运行容器"
fi
vuejsImage=$(docker images | grep mydockerhub.com:5000/vuejs | grep $tag)
echo $vuejsImage
if [ ! -n "$vuejsImage" ];
then
  echo "vuejs镜像不存在，拉取镜像"
  docker-compose pull vuejs
  if [ $? -eq 0 ];
  then
    echo "vuejs镜像拉取成功"
  else
    echo "vuejs镜像拉取失败，不存在该版本镜像"
    echo "构建vuejs该版本镜像"
    docker-compose build vuejs
    echo "将vuejs镜像上传至私库"
    docker-compose push vuejs
  fi
else
  echo "存在vuejs镜像，可以运行容器"
fi
echo "运行vue项目"
docker-compose up -d
