学习笔记

Kafka

* 设计目标：
```
持久化
性能
高吞吐率
顺序（Partition）
水平扩展
```

* 基本概念：

```
Broker：
Topic（逻辑概念）：广播给所有的消费者
Partition（物理概念）：
Producer
Consumer
Consumer Group：不同消费者组拿到的是全量消息，1组内所有消费者--相当于-->Queue，
```

* Topic和Partition：
```
一个Topic对应多个Partition，至少一个，每个Partition相当与子队里，可以在不同的机器上。
如果想要拿到Topic的所有消息，需要订阅所有的Partition
```

* Partition和Replica：
```
分区和多副本
主副本（Leader），非主副本(Follower)
```

* Topic 特性：
```
1、通过partition增加可扩展性
2、通过顺序写入达到高吞吐
3、多副本增加容错性
```

* 注释：
```
老师，一个topic, 一般分多少过partition比较合适?

讲师-秦金卫kimmking：
单个机器上，不建议有大量的partition,，，， 也不建议有大量的topic，因为他们对应着一个数据文件。。。====>单个机器上，数据文件大了， 就意味着，随机io读写了。

多个消费者能保证顺序消费么？

讲师-秦金卫kimmking：
单个partition是有序的，消费者之间没有顺序。

张**：
有点没太理解topic，可以把topic理解成数据库的数据库名吗?

讲师-秦金卫kimmking：
topic --> table
message --> row
partition --> sharding/partition
```

