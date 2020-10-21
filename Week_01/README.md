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











