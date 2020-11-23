4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；

#### 类代理方式

* jdk代理
* CGLIB代理
* ASM代理
* ByteBuddy代理
* Javassist代理


#### ByteBuddy代理依赖

```
<dependency>
    <groupId>net.bytebuddy</groupId>
    <artifactId>byte-buddy</artifactId>
    <version>1.7.1</version>
</dependency>
<dependency>
    <groupId>net.bytebuddy</groupId>
    <artifactId>byte-buddy-agent</artifactId>
    <version>1.7.1</version>
</dependency>
```

参考：

* [五种类代理的方式](https://juejin.cn/post/6883647726639120397)

* [ByteBuddy入门教程](https://zhuanlan.zhihu.com/p/151843984)

* [ByteBuddy实现动态代理](https://www.cnblogs.com/strongmore/p/13562640.html)
