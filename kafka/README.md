# 一、kafka环境准备
## （一）下载
https://kafka.apache.org/downloads
## （二）修改配置
- config/zookeeper.properties
```
dataDir=d:/tmp/kafka/zookeeper
```
- config/server.properties
```
  log.dirs=d:/tmp/kafka/logs
  log.retention.hours=168
```
## （三）启动
- 启动zk
  双击C:/dev/zookeeper/bin/zkServer.cmd
- 启动kafka
```
C:/dev/kafka_2.13-3.9.0/bin/windows/kafka-server-start.bat C:/dev/kafka_2.13-3.9.0/config/server.properties
```