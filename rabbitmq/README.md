# 一、名词解释
```
Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输, 
Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。 
Queue:消息的载体,每个消息都会被投到一个或多个队列。 
Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来. 
Routing Key:路由关键字,exchange根据这个关键字进行消息投递。 
vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。 
Producer:消息生产者,就是投递消息的程序. 
Consumer:消息消费者,就是接受消息的程序. 
Channel:消息通道,在客户端的每个连接里,可建立多个channel.
```
## 1、虚拟主机(vhost)
​ 虚拟主机：一个虚拟主机持有一组交换机、队列和绑定。虚拟主机的作用在于进行权限管控，rabbitmq默认有一个虚拟主机"/"。可以使用rabbitmqctl add_vhost命令添加虚拟主机，然后使用rabbitmqctl set_permissions命令设置指定用户在指定虚拟主机下的权限，以此达到权限管控的目的。
## 2、消息通道(channel)
消息通道:  在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务。
## 3、交换机(exchange)
​exchange的功能是用于消息分发，它负责接收消息并转发到与之绑定的队列，exchange不存储消息，如果一个exchange没有binding任何Queue，那么当它会丢弃生产者发送过来的消息，在启用ACK机制后，如果exchange找不到队列，则会返回错误。一个exchange可以和多个Queue进行绑定。

# 二、交换机类型

## 1、路由模式(Direct)

​direct 类型的行为是"先匹配, 再投送"。即在绑定时设定一个 routing_key, 消息的routing_key匹配时, 才会被交换器投送到绑定的队列中去。direct是rabbitmq的默认交换机类型。

## 2、通配符模式(Topic)

​类似路由模式，但是routing_key支持模糊匹配，按规则转发消息（最灵活）。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。

## 3、发布订阅模式(Fanout)

​转发消息到所有绑定队列，忽略routing_key。

## 4、Headers

​设置header attribute参数类型的交换机。相较于 direct 和 topic 固定地使用 routing_key , headers 则是一个自定义匹配规则的类型，忽略routing_key。在队列与交换器绑定时, 会设定一组键值对规则, 消息中也包括一组键值对( headers 属性), 当这些键值对有一对, 或全部匹配时, 消息被投送到对应队列。
​在绑定Queue与Exchange时指定一组键值对，当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配。如果完全匹配则消息会路由到该队列，否则不会路由到该队列。headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。

匹配规则x-match有下列两种类型：
**x-match = all**：表示所有的键值对都匹配才能接受到消息。
**x-match = any**：表示只要有键值对匹配就能接受到消息。

# 三、任务分发机制

# 四、消息持久化

# 五、消息序列化

# 六、延迟队列
```
1、安装支持延迟队列的插件
  https://www.rabbitmq.com/community-plugins.html
2、将解压后的ez结尾的文件放到/rabbitmq/plugins/目录下
3、启动插件：rabbitmq-plugins enable rabbitmq_delayed_message_exchange
4、重启mq
```