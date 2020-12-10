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

* application-test2.properties 配置

```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.12.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
    
<dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>shardingsphere-jdbc-core-spring-boot-starter</artifactId>
    <version>5.0.0-alpha</version>
</dependency>
```

```
spring.shardingsphere.datasource.names=primary_ds,replica_ds_0

#主库
spring.shardingsphere.datasource.primary_ds.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.primary_ds.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.primary_ds.jdbc-url=jdbc:mysql://localhost:3316/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true&useSSL=false
spring.shardingsphere.datasource.primary_ds.username=root
spring.shardingsphere.datasource.primary_ds.password=123456

#从库
spring.shardingsphere.datasource.replica_ds_0.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.replica_ds_0.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.replica_ds_0.jdbc-url=jdbc:mysql://localhost:3326/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true&useSSL=false
spring.shardingsphere.datasource.replica_ds_0.username=root
spring.shardingsphere.datasource.replica_ds_0.password=123456

#负载均衡配置
spring.shardingsphere.rules.replica-query.data-sources.pr-ds.primary-data-source-name=primary_ds
spring.shardingsphere.rules.replica-query.data-sources.pr-ds.replica-data-source-names=replica_ds_0
spring.shardingsphere.rules.replica-query.data-sources.pr-ds.load-balancer-name=round_robin
spring.shardingsphere.rules.replica-query.load-balancers.round-robin.type=ROUND_ROBIN
#负载均衡算法属性配置
spring.shardingsphere.rules.replica-query.load-balancers.round-robin.props.workId=123456
```

spring-boot 2.0 以上版本，+  shardingsphere-jdbc-core-spring-boot-starter(5.0.0-alpha)

参考：[issues/8299，Caused by: java.util.NoSuchElementException: No value bound](https://github.com/apache/shardingsphere/issues/8299)

* application-test2.properties 配置

```
spring.shardingsphere.datasource.names=primary-ds,replica-ds-0

spring.shardingsphere.datasource.common.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.common.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.common.username=root
spring.shardingsphere.datasource.common.password= 123456

#主库
spring.shardingsphere.datasource.primary-ds.jdbc-url=jdbc:mysql://localhost:3316/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true&useSSL=false
#从库
spring.shardingsphere.datasource.replica-ds-0.jdbc-url=jdbc:mysql://localhost:3326/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true&useSSL=false

#负载均衡配置
spring.shardingsphere.rules.replica-query.data-sources.pr-ds.primary-data-source-name=primary-ds
spring.shardingsphere.rules.replica-query.data-sources.pr-ds.replica-data-source-names=replica-ds-0
spring.shardingsphere.rules.replica-query.data-sources.pr-ds.load-balancer-name=round_robin
spring.shardingsphere.rules.replica-query.load-balancers.round-robin.type=ROUND_ROBIN
#负载均衡算法属性配置
spring.shardingsphere.rules.replica-query.load-balancers.round-robin.props.workId=123
spring.shardingsphere.props.sql.show=true

#配置数据源信息
#spring.datasource.url=jdbc:mysql://localhost:3316/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true&useSSL=false
#spring.datasource.username=root
#spring.datasource.password=123456
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.type=com.zaxxer.hikari.HikariDataSource
#spring.datasource.type=com.alibaba.druid.pool.DruidDataSource

#Mybatis
mybatis.type-aliases-package=com.liq.ssdemo.bean
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.configuration.map-underscore-to-camel-case=true

```
