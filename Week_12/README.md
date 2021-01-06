学习笔记

Week12 作业题目：

1.（必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群。


#### 主从复制配置

环境：MAC

redis版本：redis-4.0.11


* 1、编辑 slave-6378.conf

```
bind 127.0.0.1

slaveof 127.0.0.1 6379

port 6378

slave-read-only yes

masterauth 123456

```

* 2、启动服务

```
// 启动master
redis-server Documents/soft/redis-4.0.11/redis.conf

// 启动slave 6378
redis-server Documents/soft/redis-4.0.11/slave-6378.conf
```

```
// Successful

25419:S 06 Jan 15:26:34.091 * Connecting to MASTER 127.0.0.1:6379
25419:S 06 Jan 15:26:34.091 * MASTER <-> SLAVE sync started
25419:S 06 Jan 15:26:34.092 * Non blocking connect for SYNC fired the event.
25419:S 06 Jan 15:26:34.092 * Master replied to PING, replication can continue...
25419:S 06 Jan 15:26:34.092 * Trying a partial resynchronization (request 6697718f3cc0d579f807cd8a739c36817565c061:1).
25419:S 06 Jan 15:26:34.092 * Successful partial resynchronization with master.
25419:S 06 Jan 15:26:34.092 * MASTER <-> SLAVE sync: Master accepted a Partial Resynchronization.
```

* 3、测试

```
// master
➜  ~ redis-cli 
127.0.0.1:6379> get aa
(error) NOAUTH Authentication required.
127.0.0.1:6379> auth 123456
127.0.0.1:6379> set kk 123
OK
127.0.0.1:6379> 

127.0.0.1:6379> del kk
(integer) 1
127.0.0.1:6379> 

// slave
➜  ~ redis-cli -h 127.0.0.1 -p 6378          
127.0.0.1:6378> auth 123456
OK
127.0.0.1:6378> get kk
(nil)
127.0.0.1:6378> get kk
"123"
127.0.0.1:6378> set kk 1245
(error) READONLY You can't write against a read only slave.
127.0.0.1:6378> 

127.0.0.1:6378> get kk
(nil)
127.0.0.1:6378> 
```

```
127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:1
slave0:ip=127.0.0.1,port=6378,state=online,offset=12996,lag=0
master_replid:6697718f3cc0d579f807cd8a739c36817565c061
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:12996
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:12996


127.0.0.1:6378> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6379
master_link_status:up
master_last_io_seconds_ago:8
master_sync_in_progress:0
slave_repl_offset:13010
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:6697718f3cc0d579f807cd8a739c36817565c061
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:13010
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:13010
```


salve 启动报错，需要配置 "masterauth 123456"

```
24879:S 06 Jan 15:01:56.974 * (Non critical) Master does not understand REPLCONF listening-port: -NOAUTH Authentication required.
24879:S 06 Jan 15:01:56.974 * (Non critical) Master does not understand REPLCONF capa: -NOAUTH Authentication required.
24879:S 06 Jan 15:01:56.974 * Partial resynchronization not possible (no cached master)
24879:S 06 Jan 15:01:56.975 # Unexpected reply to PSYNC from master: -NOAUTH Authentication required.
24879:S 06 Jan 15:01:56.975 * Retrying with SYNC...
24879:S 06 Jan 15:01:56.975 # MASTER aborted replication with an error: NOAUTH Authentication required.
```

#### 配置sentinel集群

1、编辑配置文件，配置内容一样

- /soft/redis-4.0.11/sentinel.conf
- /soft/redis-4.0.11/sentinel-26380.conf
- /soft/redis-4.0.11/sentinel-26381.conf

```
// 1、配置文件在sentinel.conf配置文件中

sentinel monitor mymaster 127.0.0.1 6379 2

sentinel auth-pass mymaster 123456
```

2、分别启动

```
redis-sentinel Documents/soft/redis-4.0.11/sentinel.conf

redis-sentinel Documents/soft/redis-4.0.11/sentinel-26380.conf

redis-sentinel Documents/soft/redis-4.0.11/sentinel-26381.conf
```

3、确认：Sentinel节点本质上是一个特殊的Redis节点， 所以也可以通过info命令 来查询它的相关信息 。

```
➜  ~ redis-cli -h 127.0.0.1 -p 26379 info sentinel
# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
sentinel_simulate_failure_flags:0
master0:name=mymaster,status=ok,address=127.0.0.1:6379,slaves=2,sentinels=3
```  

4、宕机测试

关闭主节点（端口：6379），三个哨兵打印的日志如下：6377 选为主节点
      
```
51206:X 06 Jan 22:41:09.791 * +sentinel sentinel c61becca2da34b41dfdfcbe2f11cefe503d4e914 127.0.0.1 26380 @ mymaster 127.0.0.1 6379
51206:X 06 Jan 23:05:21.576 # +sdown master mymaster 127.0.0.1 6379
51206:X 06 Jan 23:05:21.852 # +new-epoch 1
51206:X 06 Jan 23:05:21.853 # +vote-for-leader 947a3ce2c0768d10c0fcccf28b3e7666b51109f3 1
51206:X 06 Jan 23:05:22.159 # +config-update-from sentinel 947a3ce2c0768d10c0fcccf28b3e7666b51109f3 127.0.0.1 26379 @ mymaster 127.0.0.1 6379
51206:X 06 Jan 23:05:22.159 # +switch-master mymaster 127.0.0.1 6379 127.0.0.1 6377
51206:X 06 Jan 23:05:22.160 * +slave slave 127.0.0.1:6378 127.0.0.1 6378 @ mymaster 127.0.0.1 6377
51206:X 06 Jan 23:05:22.160 * +slave slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6377
51206:X 06 Jan 23:05:52.179 # +sdown slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6377
```

登录：6378 查看 info replication，6378为从节点
登录：6377 查看 info replication，6377为主点

```
➜  ~ redis-cli -h 127.0.0.1 -p 6378
127.0.0.1:6378> auth 123456
OK
127.0.0.1:6378> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6377
master_link_status:up
master_last_io_seconds_ago:1
master_sync_in_progress:0
slave_repl_offset:349766
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:5a562dc1c53ca98dbcb0c3ae27b31dcdee37be0c
master_replid2:9282a51c45e7d86da500a8b56e66da8f873958c8
master_repl_offset:349766
second_repl_offset:286121
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:349766
127.0.0.1:6378> 


➜  ~ redis-cli -h 127.0.0.1 -p 6377
127.0.0.1:6377> auth 123456
OK
127.0.0.1:6377> info replication
# Replication
role:master
connected_slaves:1
slave0:ip=127.0.0.1,port=6378,state=online,offset=358201,lag=0
master_replid:5a562dc1c53ca98dbcb0c3ae27b31dcdee37be0c
master_replid2:9282a51c45e7d86da500a8b56e66da8f873958c8
master_repl_offset:358215
second_repl_offset:286121
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:358215
127.0.0.1:6377> set kk 123456
OK
127.0.0.1:6377> get kk
"123456"
127.0.0.1:6377> exit


➜  ~ redis-cli -h 127.0.0.1 -p 6378
127.0.0.1:6378> auth 123456
OK
127.0.0.1:6378> get kk
"123456"
127.0.0.1:6378> 
```

错误：

```
*** FATAL CONFIG FILE ERROR ***
Reading the configuration file, at line 71
>>> 'sentinel auth-pass mymaster 123456'
No such master with specified name.
```

[参考：Redis系列-第六篇哨兵模式](https://blog.csdn.net/niugang0920/article/details/97141175?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control)
