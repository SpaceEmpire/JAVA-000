Week05 作业题目（周六）：
```
6.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：

1）使用 JDBC 原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。
```

#### JDBC最常用的资源有三类:

* Connection: 数据库连接。
* Statement: 会话声明。
* ResultSet: 结果集游标。

#### JDBC连接池的标准的接口javax.sql.DataSource

* 1、C3P0
* 2、DBCP
* 3、Druid
* 4、HikariCP

