学习笔记

##### 五种IO模型

1、阻塞式IO，BIO

2、非阻塞式IO
和阻塞IO类比，内核会立即返回，返回后获得足够的CPU时间继续做其它的事情。
用户进程第一个阶段不是阻塞的,需要不断的主动询问kernel数据好了没有;第二个阶段依然总是阻塞的。

3、IO多路复用(IO muttiplexing)
也称事件驱动10(event-driven IO)，就是在单个线程里同时监控多个套接字，通过select或poll轮询所负责的所有
socket，当某个socket有数据到达了，就通知用户进程。
IO复用同非阻塞IO本质一样，不过利用了新的select系统调用，由内核来负责本来是请求进程该做的轮询操作。
看似比非阻塞IO还多了一个系统调用开销，不过因为可以支持多路IO，才算提高了效率。

4、信号驱动IO
信号驱动IO与BIO和NIO最大的区别就在于，在IO执行的数据准备阶段，不会阻塞用户进程。

5、异步式10
异步IO真正实现了IO全流程的非阻塞。用户进程发出系统调用后立即返回，内核等待数据准备完成，然后将数据拷贝到用户进程缓冲区，然后发送信号告诉用户进程IO操作执行完毕(与SIGIO相比，一个是发送信号告诉用户进程数据准备完毕，一个是IO执行完毕)。

##### Netty 网络应用开发框架

1.异步
2.事件驱动
3.基于NIO
适用于:服务端、客户端、TCP/UDP

Netty特性
高性能的协议服务器:高吞吐、低延迟、低开销、零拷贝、可扩容

Netty优化
1）不要阻塞EventLoop
2）系统参数优化
ulimit - a /proc/sys/net/ipv4/tcp_ fin_timeout, TcpTimedWaitDelay
3）缓冲区优化：SO_RCVBUF/SO_SNDBUF/SO_ BACKLOG/ REUSEXXX
4）心跳周期优化心跳机制与短线重连
5）内存与ByteBuffer优化DirectBuffer与HeapBuffer
6）其他优化：ioRatio、Watermark、TrafficShaping

##### Reactor模型

Reactor模式首先是事件驱动的，有一个或者多个并发输入源，有一个Service Handler和多个EventHandlers。
这个Service Handler会同步的将输入的请求多路复用的分发给相应的Event Handler。

##### API 网关

1、网关分类：业务网关，流量网关

2、常见业务网关
Zuul
Zuul2
Spring Cloud Gateway

3、网关对比
OpenResty、Kong：性能好，适合流量网关
Spring Cloud GateWay、Zuul2: 扩展性好，适合业务网关，二次开发




