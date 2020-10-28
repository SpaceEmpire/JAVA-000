学习笔记

### 第一节课

#### 指令分类

* 栈操作指令，与局部变量交互的指令
* 程序流程控制指令
* 对象操作指令，像方法调用指令
* 算数运算及类型转换指令

#### 命令

* javac  xx.java (编译)
* javap -c xx.class (查看字节码)
* javap -c -verbose xx.class

#### 方法调用指令

* invokestatic
* invokespecial
* invokevirtual
* invokeinterface
* invokedynamic

#### 类加载器

##### 类的生命周期

* 1、加载（Loading）：查找Class 文件
* 2、验证（Verification）：验证格式、依赖
* 3、准备（Preparation）：静态字段，方法表
* 4、解析（Resolution）：符号解析为引用
* 5、初始化（Initialization）：构造器、静态变量赋值、静态代码块
* 6、使用（Using）
* 7、卸载（Unloading）

##### 类的加载时机

* main 方法所在的类
* new
* 静态方法、静态字段所在的类
* 子类初始化触发父类初始化
* default
* 反射调用
* MethodHandle

##### 类不会被初始化的情况
	
* 子类引用父类静态字段，会触发父类初始化，子类不会初始化
* 类中定义了对象数组
* 常量在编译期间会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不会触发定义常量所在的类。
* 通过类名获取Class对象，不会触发类的初始化
* 通过Class.forName加载指定类时，如果指定参数initialize为false时， 也不会触发类初始化。
* 通过ClassLoader默认的loadClass方法，也不会触发初始化动作

##### 三类加载器

* 启动类加载器 BootstrapClassLoader
* 扩展类加载器 ExtClassLoader
* 应用类加载器 AppClassLoader

##### 加载器特点（避免重复加载）
	
* 双亲委派
* 负责加载
* 缓存加载

##### 添加引用类的方式

#### JVM内存模型

* 栈、堆、非堆、堆外

* 通常 -Xmx 配置不超过总内存的70%

##### 1、栈内结构

* 栈帧-> 返回值、局部变量表、操作数栈、Class/Method 指针

##### 2、堆内结构

* 老年代（大对象、存活周期长）
* 年轻代
    - 新生代
	- 存活区（S0、S1）
	- -Xmn 设置年轻代的初始值以及最大值

##### 3、非堆（方法区）

* 元数据区（Metaspace）（持久代）-> 存放对象结构、常量池
* code cache
* -XX:PermSize设置非堆内存初始值，默认是物理内存的1/64
* -XX:MaxPermSize设置最大非堆内存的大小，默认是物理内存的1/4
	

##### 直接内存（Direct Memory），不属于JVM运行时数据区的一部分


### 第二节课

#### 工具与GC策略

##### JVM命令行工具

* jps/jinfo 查看java进程
* jstat 查看JVM内部gc相关信息
 - jstat -gc 62682 1000 1000
 - jstac -gcutil 62682 1000 1000
* jmap 查看heap 或类占用空间统计
 - jmap -heap
 - jmap -histo
* jstack 查看线程信息
* jcmd 执行JVM相关分析命令（整合命令）
* jrunscript/jjs 执行js 命令

##### JVM 图形化工具

* jconsole
* jvisualvm
* VisualGC 插件IDEA
* jmc

##### GC 管理

* 仓库与引用计数

* Marking（标记）：遍历所有可达对象，并在本地内存中分门别类记下
* Sweepinig（清楚）：保证不可达对象所占内存，在之后内存分配时可以重用
* 压缩整理

* GC暂停

* 分代假设

* 年轻代和老年代采用不同的GC算法

* 年轻代：对象存活时间短，GC执行频繁

* 老年代：对象存活时间长，GC执行次数低

##### GC时对象在内存池之间转移

* 标记阶段Eden区存活的对象复制到存活区
* 控制提升阀值：-XX:+MaxTenuringThreshold=15
* 老年代默认都是存活的对象，采用移动方式

- 移动 = 立即复制到新空间 + 立即释放旧空间， 可以认为额外的内存开销很小，或者说，移动很多个内存对象时，依次移动，比如移动1G的内存，那么就不需要额外的1G内存to空间来担保。
- 复制时，一般是有个 to 空间概念的， 比如 S0或者S1，或者是老年代作为 copy-to 空间，如果这个空间不足就会报错。
- 为什么采用复制算法，是由于分代假设以及GC的实现方式决定的，每次GC之后eden区都会整个“清空”，所以不在乎浪费一点空间。

##### 可以作为GC Roots 对象

* 1、当前正在执行的方法里的局部变量和输入参数
* 2、活动线程
* 3、所有类的静态字段
* 4、JNI引用

* 注意：跨代引用，由专门的集合处理

##### GC三个处理步骤，三者优缺点

* 清除算法：标记-清除
* 复制算法：标记-复制算法
* 整理算法：标记-清除-整理算法

##### GC算法

* 串行GC (Serial GC) / ParNewGC （并行GC）

- -XX:+UseSerialGC 配置串行GC
- -xx:+UseParNewGC 改进版的 Serial GC，可以配合CMS 使用

* 并行GC (Parallel GC)

- XX:+UseParallelGC
- XX:+UseParallelOldGC
- XX:+UseParallelGC -XX:ParallelOldGC

* 年轻代和老年代的垃圾回收都会触发STW事件。
- 在年轻代使用标记-复制(mark-copy)算法，吃老年代使用标记-清除一整理(mark- sweep-compact)算法。
- -XX:ParallelGCThreads=N来指定GC线程数，其默认值为CPU核心数。

* 并行垃圾收集器适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到更高的吞吐量:
- 在GC期间，所有CPU内核都在并行清理垃圾，所以总暂停时间更短;
- 在两次GC周期的间隔期，没有GC线程在运行，不会消耗任何系统资源。

* JDK8及以前默认是并行GC，之后默认是G1


###### CMS GC (Mostly Concurrent Mark and Sweep Garbage Collector) (并发)

- -XX:+UseConcMarkSweepGC

```
其对年轻代采用并行STW方式的mark- -copy(标记-复制)算法，对老年代主要使用并发mark-sweep(标记-清除)算法。

CMS GC的设计目标是避免在老年代垃圾收集时出现长时间的卡顿，主要通过两种手段来达成此目标:
1.不对老年代进行整理，而是使用空闲列表free-lists)来管理内存空间的回收。
2.在mark-and-sweep(标记-清除) 阶段的大部分工作和应用线程一 起并发执行。

也就是说，在这些阶段并没有明显的应用线程暂停。但值得注意的是，它仍然和应用线程争抢CPU时间。默认情况下，CMS使用的并发线程数等于CPU核心数的1/4。

如果服务器是多核CPU，并且主要调优目标是降低GC停顿导致的系统延迟，那么使用CMS是个很明智的选择。进行老年代的并发回收时，可能会伴随着多次年轻代的minor GC。
```
* 思考:并行Parallel与并发Concurrent的区别?

```
阶段1:Initial Mark(初始标记)
阶段2:Concurrent Mark(并发标记)
阶段3:Concurrent Preclean(并发预清理)
阶段4:Final Remark(最终标记)
阶段5:Concurrent Sweep(并发清除)
阶段6:Concurrent Reset (并发重置)
```

###### G1 GC



































