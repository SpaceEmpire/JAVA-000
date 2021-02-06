学习笔记


#### Mysql事物

事务可靠性模型ACID:

```
Atomicity：原子性，一次事务中的操作要么全部成功，要么全部失败。

Consistency：一致性,跨表、跨行、跨事务,数据库始终保持一致状态。

Isolation：隔离性,可见性,保护事务不会互相干扰，包含4种隔离级别。

Durability：持久性,事务提交成功后，不会丢数据。如电源故障，系统崩溃。

```

##### 表级锁

##### 行级锁


死锁:
阻塞与互相等待
增删改、锁定读
死锁检测与自动回滚
锁粒度与程序设计


dump 会锁表或库

##### 隔离级别

《SQL:1992标准》规定了四种事务隔离级别(lsolation):

```
读未提交:READ UNCOMMITTED
读已提交:READ COMMITTED
可重复读:REPEATABLE READ
可串行化:SERIALIZABLE
```

MySQL:

可以设置全局的默认隔离级别

可以单独设置会话的隔离级别

InnoDB实现与标准之间的差异

```
undo log：撤销日志（保证事物回滚的一致性）
redo log：重做日志（保证事物提交的一致性）
```


主键索引：主键索引的叶子节点保存着主键及对应行的全部数据。在InnoDB里，主键索引也被称为聚簇索引

二级索引（非主键索引）：二级索引树中的叶子节点保存索引值和主键值，当使用二级索引进行查询时，需要进行回表。在InnoDB里，非主键索引也被称为二级索引

覆盖索引：当sql语句的所求查询字段（select列）和查询条件字段（where子句）全都包含在一个索引中（联合索引），可以直接使用索引查询而不需要回表。

最左匹配原则

索引下推

覆盖索引、联合索引、索引下推 (https://www.cnblogs.com/Leo_wl/p/13093705.html)


##### SQL 性能与优化

```
// 查看表结构
show columns from test;

// 查看表创建语句
show create table test;

```

* mysql 执行流程

* mysql 执行引擎和状态

* mysql 对sql的执行顺序

```
1、from
2、on
3、join
4、where
5、group by
6、having + 聚合函数
7、select (获取哪些列)
8、order by
9、limit 
```

* mysql 索引原理

```
innoDB 使用B+树实现聚集索引

bigint： 8字节
指针：6字节

主机一共占：14字节
一页在mysql默认大小是：16k

16k * 1024 / 14b = 1170

高度2的b+树有16个块：16*1170=18724条数据
```


##### Mysql 配置优化

推荐书籍：mysql技术内幕、数据库系统全书、数据密集型应用系统设计

查看参数配置：show variables;

```
mysql -hlocalhost -P3306 -uroot -e "show variables" > /Users/liquan/Documents/mysql.txt

mysql -hlocalhost -3306 -uroot -e "select * from test.test"
```

my.cnf 文件

```
[mysqld]
server

[mysql]
client
```

1）连接请求变量（跟连接有关）

```
1、max_connections  最大连接数（5000-10000） ☆☆☆☆☆
2、back_log
3、wait_timeout 和 interative_timeout
```

2）缓冲区变量（跟性能有关）【不建议每次查询的数据量过大】

```
4、key_buffer_size
5、query_cache_size(查询缓存简称QC) ☆☆☆☆☆
6、max_connect_errors:
7、sort_buffer_size:
8、max_allowed_packet=32M
9、join_buffer_size=2M
10、thread_cache_size=300
```

3）配置Innodb的几个变量

```
11、innodb_buffer_pool_size   ☆☆☆☆☆
12、innodb_flush_log_at_trx_commit
13、innodb_thread_concurrency=0  ☆☆☆☆☆
14、innodb_log_buffer_size   ☆☆☆☆☆
15、innodb_log_file_size=50M ☆☆☆☆☆
16、innodb_log_files_in_group=3
17、read_buffer_size=1M   ☆☆☆☆☆
18、read_rnd_buffer_size=16M
19、bulk_insert_buffer_size=64M  ☆☆☆☆☆
20、binary log
```

##### 数据库设计优化-最佳实践

```
-如何恰当选择引擎?

-库表如何命名?

-如何合理拆分宽表?

-如何选择恰当数据类型：明确、尽量小

    - char、varchar 的选择

    - (text/blob/clob)的使用问题?（尽量少用）
    
    -【参考：数据库系统全书】

    - 文件、图片是否要存入到数据库?

-时间日期的存储问题? 注意时区问题、建议存时间戳（bigint）

—数值的精度问题? 会丢失精度， 如果很长可以用 整数或字符串存

-是否使用外键、触发器? 不建议用
```

```
-唯一约束和索引的关系?

-是否可以冗余字段? 可以使用

-是否使用游标、变量、视图、自定义函数、存储过程? 都不建议用

-自增主键的使用问题?

-能够在线修改表结构(DDL操作)? 会锁表，mysqldump 默认锁表，夜深人静的时候

-逻辑删除还是物理删除? 逻辑删除

-要不要加create_time，update_ time时间戳? 非常建议加

-数据库碎片问题? 重复建索引问题、可以压缩

-如何快速导入导出、备份数据?  用mysql 原生命令要比 jdbc 批量导入快很多，导入数据的时候，可以先去除索引
```
