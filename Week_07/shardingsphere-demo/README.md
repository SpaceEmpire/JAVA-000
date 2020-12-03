Week07 作业题目（周六）：

3.（必做）读写分离 - 数据库框架版本 2.0

##### sharding-jdbc-spring-boot-starter

* application-test1.properties 配置

```
spring.shardingsphere.datasource.names=master,slave0

#主库
spring.shardingsphere.datasource.master.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.master.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.master.jdbc-url=jdbc:mysql://localhost:3316/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true&useSSL=false
spring.shardingsphere.datasource.master.username=root
spring.shardingsphere.datasource.master.password=123456

#从库
spring.shardingsphere.datasource.slave0.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.slave0.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.slave0.jdbc-url=jdbc:mysql://localhost:3326/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true&useSSL=false
spring.shardingsphere.datasource.slave0.username=root
spring.shardingsphere.datasource.slave0.password=123456

#读写分离配置
spring.shardingsphere.masterslave.load-balance-algorithm-type=round_robin
spring.shardingsphere.masterslave.name=ms
spring.shardingsphere.masterslave.master-data-source-name=master
spring.shardingsphere.masterslave.slave-data-source-names=slave0
spring.shardingsphere.props.sql.show=true
```
* pom.xml

``` 
<dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
    <version>4.0.1</version>
</dependency>
```

* 测试返回结果：

```
2020-12-03 17:39:27.260  INFO 12332 --- [main] ShardingSphere-SQL: Rule Type: master-slave
2020-12-03 17:39:27.260  INFO 12332 --- [main] ShardingSphere-SQL: SQL: update `user` set nickname = ? where id = ? ::: DataSources: master
修改返回结果：1
2020-12-03 17:39:27.327  INFO 12332 --- [main] ShardingSphere-SQL: Rule Type: master-slave
2020-12-03 17:39:27.327  INFO 12332 --- [main] ShardingSphere-SQL: SQL: select * from `user` where id = ? ::: DataSources: slave0
查询返回结果：User(id=1, nickname=小贾, mobile=19916728223, createTime=0, updateTime=0)
```

##### shardingsphere-jdbc-core-spring-boot-starter

```
<dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>shardingsphere-jdbc-core-spring-boot-starter</artifactId>
    <version>5.0.0-alpha</version>
</dependency>
```

暂时没有配置成功