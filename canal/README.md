# mysql
```
开启 MySQL的binlog（vi /etc/my.cnf）

log-bin=mysql-bin # 开启 binlog
binlog-format=ROW # 选择 ROW 模式
server_id=1       # 配置 MySQL replaction 需要定义，不要和 canal 的 slaveId 重复

重启mysql
```

# rabbitmq
```
创建queue=canal-mysql
exchange（direct）=canal-mysql
routekey=mysql-binlog
```

# canal
## 安装canal
```
拉取镜像
docker pull canal/canal-server
docker pull canal/canal-admin


启动镜像
docker run --name canal-server -d canal/canal-server:latest


将容器内部配置文件拷贝到外部
docker cp canal-server:/home/admin/canal-server/conf/canal.properties /data/canal
docker cp canal-server:/home/admin/canal-server/conf/example/instance.properties /data/canal
```

## 修改canal配置
```
vi /tmp/canal/conf/canal.properties
canal.serverMode = rabbitMQ
rabbitmq.host =192.168.15.67
rabbitmq.virtual.host =hela_credit_vhost
rabbitmq.exchange =canal-mysql
rabbitmq.username =wandao
rabbitmq.password =welcome123
rabbitmq.deliveryMode =direct


vi /tmp/canal/conf/example/instance.properties
canal.instance.master.address = 192.168.15.67:3306
#rabbitmq中exchange与queue进行绑定的路由键
canal.mq.topic=mysql-binlog
#mysql数据库账号密码
canal.instance.dbUsername = root
canal.instance.dbPassword = welcome123
```

## 重启canal
```
重新安装
docker stop canal-server
docker rm canal-server

docker run --name canal-server -p 11111:11111 -d -v /data/canal/instance.properties:/home/admin/canal-server/conf/example/instance.properties -v /data/canal/canal.properties:/home/admin/canal-server/conf/canal.properties canal/canal-server:latest
```