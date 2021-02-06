学习笔记

一、RPC原理

1)本地代理存根:Stub
2)本地序列化反序列化
3)网络通信
4)远程序列化反序列化
5)远程服务存根:Skeleton
6)调用实际业务服务
7)原路返回服务结果
8)返回给本地调用方

1、RPC原理-设计

RPC是基于接口的远程服务调用。
本地应用程序与远程应用程序，分别需要共享什么信息，角色有什么不同?
共享：POJO实体类定义，接口定义。
REST/PB下，真的不需要嘛?另一种选择：WSDL/WADL/IDL
远程->服务提供者，本地->服务消费者。

2、RPC原理-代理

RPC是基于接口的远程服务调用。
Java下，代理可以选择动态代理，或者AOP实现
C#直接有远程代理
Flex可以使用动态方法和熟悉

3、RPC原理-网络传输

TCP/SSL
HTTP/HTTPS

4、RPC原理-查找实现类

通过接口查找服务端的实现类。
一般是注册方式，
例如dubbo默认将接口和实现类配置到Spring

二、RPC技术框架

Corba/RMI/.NET Remoting
JSON RPC,XML RPC, WebService(Axis2, CXF)
Hessian, Thrift, Protocol Buffer, gRPC

三、Dubbo

Apache Dubbo是一款高性能、轻量级的开源Java服务框架
六大核心能力：面向接口代理的高性能RPC调用，智能容错和负载均衡，服务自动注册和发现，高度可扩展能力，运行期流量调度，可视化的服务治理与运维。

1、Dubbo的主要功能

基础功能：RPC调用
多协议(序列化、传输、RPC)一服务注册发现
配置、元数据管理
框架分层设计，可任意组装和扩展。

扩展功能:集群、高可用、管控一集群，负载均衡
一治理，路由，
一控制台，管理与监控
灵活扩展+简单易用，是Dubbo成功的秘诀。

2、Dubbo技术原理

1)config配置层:对外配置接口，以ServiceConfig, ReferenceConfig为中心，可以直接初始化配置类，也可以通过spring解析配置生成配置类
2)proxy服务代理层:服务接口透明代理，生成服务的客户端Stub和服务器端Skeleton,以ServiceProxy为中心，扩展接口为ProxyFactory
3)registry注册中心层: 封装服务地址的注册与发现，以服务URL为中心，扩展接口为RegistryFactory, Registry, RegistryService
4)cluster路由层:封装多个提‘供者的路由及负载均衡，并桥接注册中心，以Invoker为中心，扩展接口为Cluster, Directory, Router, LoadBalance
5)monitor监控层: RPC调用次数和调用时间监控，以Statistics为中心，扩展接口为MonitorFactory, Monitor, MonitorService
6)protocol远程调用层:封装RPC调用，以Invocation, Result为中心，扩展接口为Protocol, Invoker, Exporter
7)exchange信息交换层: 封装请求响应模式，同步转异步，以Request, Response为中心，扩展接口为Exchanger, ExchangeChannel, ExchangeClient, ExchangeServer
8)transport网络传输层:抽象mina和netty为统一接口，以Message为中心，扩展接口为Channel, Transporter, Client, Server, Codec
9)serialize数据序列化层:可复用的一 些工具，扩展接口为 Serialization, Objectlnput,ObjectOutput, ThreadPool

