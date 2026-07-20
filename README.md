# Java 技术栈学习示例

> 📚 涵盖 Java 后端开发中常用技术栈的实战示例，每个模块独立可运行，适合学习和查阅。

![Java](https://img.shields.io/badge/Java-8+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.x-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

---

## 📖 简介

本项目是一个 **Java 后端技术栈示例集合**，每个子模块对应一项具体技术，代码简洁、可直接运行。涵盖：

- 🏗️ 设计模式与算法
- 🌱 Spring 全家桶（Security、Event、生命周期、状态机、Cloud Gateway）
- 💾 数据库与缓存（JPA、MyBatis-Plus、Redis、MongoDB、Elasticsearch、分库分表）
- 📨 消息队列（RabbitMQ、RocketMQ、Kafka）
- 🔗 微服务与 RPC（gRPC、OpenFeign、Retrofit、WebService）
- 🔐 安全框架（Spring Security、Shiro、加解密）
- 🛠️ 实用工具（Excel、PDF、Protobuf、Docker、限流、WebSocket 等）

---

## 🚀 快速开始

```bash
# 克隆项目
git clone https://github.com/luckyQing/demo.git

# 编译（跳过测试）
mvn clean install -DskipTests
```

> 各模块相对独立，进入感兴趣的子模块目录查看其 README 即可。

---

## 📂 模块目录

### 🏗️ 基础与算法

| 模块 | 说明 |
|------|------|
| [algorithm](algorithm) | 算法与数据结构 |
| [design-patterns](design-patterns) | 设计模式（工厂、策略、代理、单例、模板方法等） |
| [skills](skills) | Java 基础知识（多线程、集合、网络、Lambda、反射等） |
| [jmh](jmh) | JMH 性能基准测试 |

### 🌱 Spring 生态

| 模块 | 说明 |
|------|------|
| [spring5](spring5) | Spring 5 响应式编程（WebFlux） |
| [spring-life-cycle](spring-life-cycle) | Spring Bean 生命周期 |
| [spring-event](spring-event) | Spring 事件驱动机制 |
| [spring-statemachine](spring-statemachine) | Spring 状态机 |
| [spring-cloud-gateway](spring-cloud-gateway) | Spring Cloud Gateway 网关 |
| [api-version](api-version) | API 版本管理 |

### 💾 数据库与缓存

| 模块 | 说明 |
|------|------|
| [jpa](jpa) | Spring Data JPA |
| [mybatis-plus](mybatis-plus) | MyBatis-Plus |
| [tkmybatis](tkmybatis) | TK MyBatis 通用 Mapper |
| [sharding-jdbc](sharding-jdbc) | ShardingSphere 分库分表 |
| [flyway](flyway) | Flyway 数据库版本管理 |
| [redis](redis) | Redis 操作示例 |
| [mongodb](mongodb) | MongoDB 操作示例 |
| [elasticsearch](elasticsearch) | Elasticsearch 搜索示例 |

### 📨 消息队列

| 模块 | 说明 |
|------|------|
| [rabbitmq](rabbitmq) | RabbitMQ 消息队列 |
| [rocketmq](rocketmq) | RocketMQ（含事务消息） |
| [kafka](kafka) | Kafka 消息队列 |

### 🔗 微服务与 RPC

| 模块 | 说明 |
|------|------|
| [grpc-module](grpc-module) | gRPC 服务提供与消费 |
| [openfeign](openfeign) | OpenFeign 声明式调用 |
| [retrofit](retrofit) | Retrofit HTTP 客户端 |
| [webservice-server](webservice-server) | WebService 服务端（CXF） |
| [webservice-client](webservice-client) | WebService 客户端 |
| [jws-webservice](jws-webservice) | JAX-WS 原生 WebService |

### 🔐 安全与权限

| 模块 | 说明 |
|------|------|
| [spring-security](spring-security) | Spring Security（Servlet） |
| [spring-security-webflux](spring-security-webflux) | Spring Security（WebFlux） |
| [shiro-servlet](shiro-servlet) | Apache Shiro（Servlet） |
| [shiro-webflux](shiro-webflux) | Apache Shiro（WebFlux） |
| [encryption](encryption) | 加解密算法示例 |

### 🛠️ 工具与中间件

| 模块 | 说明 |
|------|------|
| [excel](excel) | Excel 读写（EasyExcel / POI） |
| [html2pdf](html2pdf) | HTML 模板生成 PDF |
| [protobuf](protobuf) | Protocol Buffers 序列化 |
| [protostuff](protostuff) | Protostuff 序列化 |
| [http-message-converter](http-message-converter) | HTTP 消息转换器（XML/JSON/Protobuf） |
| [hibernate-validator](hibernate-validator) | 参数校验 |
| [logback](logback) | Logback 日志配置 |
| [docker](docker) | Docker 容器化部署 |
| [canal](canal) | Canal 监听 MySQL Binlog |
| [binlog](binlog) | MySQL Binlog 解析 |
| [bytebuddy](bytebuddy) | ByteBuddy 字节码增强 |
| [quasar](quasar) | Quasar 轻量级协程 |
| [rate-limiter](rate-limiter) | 接口限流 |
| [distributed-transaction](distributed-transaction) | 分布式事务方案 |

### 🌐 WebSocket

| 模块 | 说明 |
|------|------|
| [websocket-server](websocket-server) | WebSocket 服务端 |
| [websocket-client](websocket-client) | WebSocket 客户端 |

### 🧪 测试

| 模块 | 说明 |
|------|------|
| [junit5](junit5) | JUnit 5 单元测试 |
| [mocktest](mocktest) | Mockito Mock 测试 |

---

## 🛠️ 技术栈

- **语言**：Java 8+
- **构建工具**：Maven
- **核心框架**：Spring Boot 2.x / Spring 5
- **ORM**：JPA、MyBatis-Plus、TK MyBatis
- **数据库**：MySQL、MongoDB、Redis、Elasticsearch
- **消息队列**：RabbitMQ、RocketMQ、Kafka
- **RPC**：gRPC、OpenFeign、Retrofit、WebService
- **安全**：Spring Security、Apache Shiro
- **其他**：Docker、Flyway、ShardingSphere、Protobuf、ByteBuddy、Quasar

---

## 📌 环境要求

- JDK 8+
- Maven 3.x
- 部分模块需要本地启动对应中间件（Redis、RabbitMQ、RocketMQ 等），具体见各模块 README

---

## 📄 License

[MIT](LICENSE)

---

> ⭐ 如果对你有帮助，欢迎 **Star** 支持一下！
