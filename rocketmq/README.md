# 一、安装
- 下载server
  https://rocketmq.apache.org/zh/download
- 下载console
  https://github.com/apache/rocketmq-dashboard/releases/tag/rocketmq-dashboard-2.0.0

# 二、启动
1、先启动name server
```
start D:/program/rocketmq/bin/mqnamesrv.cmd
```
2、再启动broker
```
start D:/program/rocketmq/bin/mqbroker.cmd -n 0.0.0.0:9876 -c D:/program/rocketmq/conf/broker.conf
```
3、启动console
3.1、打包
```
mvn clean package -Dmaven.test.skip=true -T 4
```
3.2、启动
```
java -jar D:/program/rocketmq-dashboard-2.0.0.jar
```
3.3访问console
http://localhost:8080