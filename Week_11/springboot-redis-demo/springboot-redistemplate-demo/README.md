
【异常】nested exception is java.lang.NoClassDefFoundError: redis/clients/jedis/util/SafeEncoder

版本冲突

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
		
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.3.0</version>
</dependency>
```

[参考：NoClassDefFoundError:SafeEncoder](https://blog.csdn.net/weixin_42697074/article/details/103818612)
