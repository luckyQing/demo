# mongodb相关实例
## 1、安装
```
1、下载mongodb
   https://www.mongodb.com/dr/fastdl.mongodb.org/win32/mongodb-win32-x86_64-2008plus-ssl-4.0.6.zip/download
2、下载客户端MongoBooster
   https://nosqlbooster.com/downloads
3、启动
  3.1创建目录：“D:/data/db”
  3.2cmd执行命令：“D:/software_install/mongodb4.0.6/bin/mongod --dbpath D:/data/db”
```

2、概念
```
MongoDB 是一个基于分布式文件存储的数据库
```
| SQL术语/概念 | MongoDB术语/概念 | 解释/说明 |
| - | - | - |
| database | database | 数据库 |
| table | collection | 数据库表/集合 |
| row | document | 数据记录行/文档 |
| column | field | 数据字段/域 |
| index | index | 索引 |
| table joins | &nbsp; | 表连接,MongoDB不支持 |
| primary key | primary key | 主键,MongoDB自动将_id字段设置为主键 |