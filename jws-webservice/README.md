> jax-ws

# 一、访问地址
```
http://localhost:7777/ns?wsdl
```
# 二、java自带工具生成Webservice客户端代码
```
1、代码生成命令
   wsimport -encoding utf-8 -s E:\webservice_test -p com.liyulin.jws.webservice.client.service http://localhost:7777/ns?wsdl
2、参数说明
   -encoding ：指定编码格式（此处是utf-8的指定格式）
   -s：指定.java文件的输出目录
   -p：定义生成类的包名，不定义的话有默认包名

   -keep：是否生成Java源文件
   -d：指定.class文件的输出目录
   -verbose：在控制台显示输出信息
   -b：指定jaxws/jaxb绑定文件或额外的schemas
   -extension：使用扩展来支持SOAP1.2
```