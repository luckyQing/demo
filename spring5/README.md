# spring5新特性——响应式编程 #

----------
# 一、概念

## （一）WebFlux 简介
```
Spring WebFlux是Spring Framework 5.0中引入的新的反应式Web框架。与Spring MVC不同，它不需要Servlet API，完全异步且无阻塞，并通过Reactor项目实现Reactive Streams规范。
```
Spring WebFlux有两种编程模型：
- 基于 Spring MVC 注解 @Controller 等
- 基于 Functional 函数式路由

```
NOTE：如果你再pom中同时引用了spring-boot-starter-web和 spring-boot-starter-webflux的话，优先会使用spring-boot-starter-web。
```

## （二）错误处理
```
Spring Boot还提供了一个方便AbstractErrorWebExceptionHandler的让你以WebFlux功能方式处理错误。
```

## （三）Webflux 过滤器
```
Spring WebFlux提供了一个WebFilter可以实现过滤HTTP请求 - 响应交换的接口。WebFilter在应用程序上下文中找到的bean将自动用于过滤每个exchange。
```

## （二）Mono
```
Mono实现发布者，并返回0或1个元素，即单对象。
```
**Mono常用的方法：**
- Mono.create()：使用 MonoSink 来创建 Mono。
- Mono.justOrEmpty()：从一个 Optional 对象或 null 对象中创建 Mono。
- Mono.error()：创建一个只包含错误消息的 Mono。
- Mono.never()：创建一个不包含任何消息通知的 Mono。
- Mono.delay()：在指定的延迟时间之后，创建一个 Mono，产生数字 0 作为唯一值。

```
NOTE：为啥不直接返回对象，比如返回City/Long/List。原因是，直接使用Flux和Mono是非阻塞写法，相当于回调方式。利用函数式可以减少了回调，因此会看不到相关接口。这恰恰是 WebFlux 的好处：集合了非阻塞 + 异步。
```

## （三）Flux
```
Flux实现发布者，并返回N个元素，即List列表对象。
有三种方法可以创建Flux序列，分别是静态方法、generate()方法和create()方法。如下代码：
```