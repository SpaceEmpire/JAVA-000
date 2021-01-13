周六作业：
1.（必做）搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。


1、修改配置文件

因为是单机部署，需要准备三个配置文件

```
config/server9092.properties

config/server9093.properties

config/server9094.properties
```
```
#改为0，1，2
broker.id=0

#改为9092，9093，9094
listeners=PLAINTEXT://:9092

#改为/tmp/kafka-logs1，/tmp/kafka-logs2，/tmp/kafka-logs3
log.dirs=/tmp/kafka-logs1
```

2、启动服务

```
#启动zookeeper
./bin/zookeeper-server-start.sh config/zookeeper.properties

#分别启动三个kafka服务
./bin/kafka-server-start.sh config/server9092.properties

./bin/kafka-server-start.sh config/server9093.properties

./bin/kafka-server-start.sh config/server9094.properties
```

3、创建topic，3个partitions，2个副本

```
./bin/kafka-topics.sh --zookeeper localhost:2181 --partitions 3 --replication-factor 2 --create --topic test01

./bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test01 
Topic: test01	PartitionCount: 3	ReplicationFactor: 2	Configs: 
	Topic: test01	Partition: 0	Leader: 1	Replicas: 1,2	Isr: 1,2
	Topic: test01	Partition: 1	Leader: 2	Replicas: 2,0	Isr: 2,0
	Topic: test01	Partition: 2	Leader: 0	Replicas: 0,1	Isr: 0,1
```

4、发送消息

```
➜  kafka_2.12-2.7.0 ./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test01
>123
>hello world
>hi
>
```

5、消费消息

```
➜  kafka_2.12-2.7.0 ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic test01
123
hello world
hi
```

6、生产者性能测试

```
➜  kafka_2.12-2.7.0 ./bin/kafka-producer-perf-test.sh --topic test01 --num-records 100000 --record-size 1000 --throughput 2000 --producer-props bootstrap.servers=localhost:9092
10002 records sent, 2000.0 records/sec (1.91 MB/sec), 10.1 ms avg latency, 474.0 ms max latency.
10004 records sent, 2000.8 records/sec (1.91 MB/sec), 1.1 ms avg latency, 20.0 ms max latency.
10001 records sent, 2000.2 records/sec (1.91 MB/sec), 1.6 ms avg latency, 42.0 ms max latency.
10008 records sent, 2001.2 records/sec (1.91 MB/sec), 1.0 ms avg latency, 31.0 ms max latency.
10009 records sent, 2001.0 records/sec (1.91 MB/sec), 0.7 ms avg latency, 35.0 ms max latency.
10004 records sent, 2000.4 records/sec (1.91 MB/sec), 0.8 ms avg latency, 43.0 ms max latency.
10000 records sent, 2000.0 records/sec (1.91 MB/sec), 0.6 ms avg latency, 18.0 ms max latency.
10004 records sent, 2000.0 records/sec (1.91 MB/sec), 0.8 ms avg latency, 46.0 ms max latency.
10004 records sent, 2000.8 records/sec (1.91 MB/sec), 1.9 ms avg latency, 70.0 ms max latency.
100000 records sent, 1999.560097 records/sec (1.91 MB/sec), 2.03 ms avg latency, 474.00 ms max latency, 1 ms 50th, 4 ms 95th, 53 ms 99th, 93 ms 99.9th.

➜  kafka_2.12-2.7.0 ./bin/kafka-producer-perf-test.sh --topic test01 --num-records 100000 --record-size 1000 --throughput 2000 --producer-props bootstrap.servers=localhost:9093
9998 records sent, 1999.6 records/sec (1.91 MB/sec), 5.9 ms avg latency, 479.0 ms max latency.
10006 records sent, 2000.4 records/sec (1.91 MB/sec), 0.6 ms avg latency, 5.0 ms max latency.
10008 records sent, 2000.8 records/sec (1.91 MB/sec), 1.3 ms avg latency, 49.0 ms max latency.
10002 records sent, 2000.0 records/sec (1.91 MB/sec), 4.1 ms avg latency, 148.0 ms max latency.
10004 records sent, 2000.8 records/sec (1.91 MB/sec), 0.5 ms avg latency, 9.0 ms max latency.
10000 records sent, 2000.0 records/sec (1.91 MB/sec), 0.6 ms avg latency, 31.0 ms max latency.
10002 records sent, 2000.4 records/sec (1.91 MB/sec), 0.7 ms avg latency, 23.0 ms max latency.
10005 records sent, 2000.2 records/sec (1.91 MB/sec), 0.6 ms avg latency, 11.0 ms max latency.
10005 records sent, 2000.6 records/sec (1.91 MB/sec), 2.3 ms avg latency, 89.0 ms max latency.
100000 records sent, 1999.440157 records/sec (1.91 MB/sec), 1.99 ms avg latency, 479.00 ms max latency, 1 ms 50th, 4 ms 95th, 44 ms 99th, 86 ms 99.9th.

➜  kafka_2.12-2.7.0 ./bin/kafka-producer-perf-test.sh --topic test01 --num-records 100000 --record-size 1000 --throughput 2000 --producer-props bootstrap.servers=localhost:9094
10000 records sent, 2000.0 records/sec (1.91 MB/sec), 4.4 ms avg latency, 444.0 ms max latency.
10006 records sent, 2000.4 records/sec (1.91 MB/sec), 1.8 ms avg latency, 56.0 ms max latency.
10004 records sent, 2000.0 records/sec (1.91 MB/sec), 0.7 ms avg latency, 45.0 ms max latency.
10004 records sent, 2000.4 records/sec (1.91 MB/sec), 0.7 ms avg latency, 17.0 ms max latency.
10008 records sent, 2000.8 records/sec (1.91 MB/sec), 0.9 ms avg latency, 38.0 ms max latency.
10004 records sent, 2000.4 records/sec (1.91 MB/sec), 0.9 ms avg latency, 26.0 ms max latency.
10004 records sent, 2000.4 records/sec (1.91 MB/sec), 0.8 ms avg latency, 17.0 ms max latency.
10002 records sent, 2000.0 records/sec (1.91 MB/sec), 0.9 ms avg latency, 17.0 ms max latency.
10006 records sent, 2000.4 records/sec (1.91 MB/sec), 0.8 ms avg latency, 25.0 ms max latency.
100000 records sent, 1999.480135 records/sec (1.91 MB/sec), 1.30 ms avg latency, 444.00 ms max latency, 1 ms 50th, 3 ms 95th, 25 ms 99th, 45 ms 99.9th.
```

7、消费者性能测试

```
➜  kafka_2.12-2.7.0 ./bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9092 --topic test01 --fetch-size 1048576 --messages 100000 --threads 1 --timeout 100000
WARNING: option [threads] and [num-fetch-threads] have been deprecated and will be ignored by the test
start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
2021-01-13 09:22:14:062, 2021-01-13 09:22:15:197, 95.7060, 84.3225, 100358, 88421.1454, 1610500934651, -1610500933516, -0.0000, -0.0001
```
