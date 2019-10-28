# 基础镜像 
FROM openjdk:8-jdk-alpine 
# 对应pom.xml文件中的dockerfile-maven-plugin插件buildArgs配置项JAR_FILE的值 
ARG JAR_FILE 
# 复制打包完成后的jar文件到/opt目录下 
COPY ${JAR_FILE} /opt/dockerapp.jar 
# 启动容器时执行 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/dockerapp.jar"] 
EXPOSE 10001