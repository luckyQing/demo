# 方式一
spring boot功能包含Dockerfile文件
pom.xml build节点添加
```
<plugin>
	<groupId>com.spotify</groupId>
	<artifactId>dockerfile-maven-plugin</artifactId>
	<version>1.4.13</version>
	<configuration>
		<repository>dockerapp</repository>
		<contextDirectory>./</contextDirectory>
		<tag>${project.version}</tag>
		<buildArgs>
			<JAR_FILE>target/${project.artifactId}-${project.version}.jar</JAR_FILE>
		</buildArgs>
	</configuration>
</plugin>
```

```
sudo mvn clean package dockerfile:build
sudo docker run -p 8080:10001 -t dockerapp:0.0.1-SNAPSHOT
```


# 方式二
spring boot项目不包含docker相关内容。
在springboot.jar同目录下新建Dockerfile文件，内容
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY docker-0.0.1-SNAPSHOT.jar /opt/dockerapp.jar 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/dockerapp.jar"]
```

```
制作镜像 sudo docker build -t dockerapp  .
启动容器 sudo docker run -p 8080:10001 -d dockerapp
```