
2、（选做）分别基于jedis，RedisTemplate，Lettuce，Redission实现redis基本操作 的demo，可以使用spring-boot集成上述工具。

##### springboot+jedis

```
1、pom.xml 引入依赖
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.10.2</version>
</dependency>

2、redis-jedis.properties

4、加载redis配置和注册jedis连接池com.liq.demo.config.RedisJedisConfig

5、封装redis基本操作：com.liq.demo.utils.RedisRedisUtil

````

[参考：SpringBoot使用Jedis整合Redis](https://blog.csdn.net/zhulier1124/article/details/82193182?utm_medium=distribute.pc_relevant.none-task-blog-searchFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-searchFromBaidu-1.control)


[参考：Redis分布式锁的实现原理](https://mp.weixin.qq.com/s?__biz=MzU0OTk3ODQ3Ng==&mid=2247483893&idx=1&sn=32e7051116ab60e41f72e6c6e29876d9&chksm=fba6e9f6ccd160e0c9fa2ce4ea1051891482a95b1483a63d89d71b15b33afcdc1f2bec17c03c&mpshare=1&scene=23&srcid=1121Vlt0Mey0OD5eYWt8HPyB#rd)
