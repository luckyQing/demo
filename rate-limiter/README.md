# 控制台下载地址
https://github.com/alibaba/Sentinel/releases

# sentinel控制台启动
```
java -jar -Dserver.port=10002 -Dsentinel.dashboard.auth.username=sentinel -Dsentinel.dashboard.auth.password=123456 sentinel-dashboard.jar
```

# 规则持久化方式
目前 Sentinel 中默认实现了5种规则持久化的方式，分别是：file、redis、nacos、zk和apollo。
## File

参考地址：https://github.com/all4you/sentinel-tutorial