


### Spring framework 6大模块

```
1. Core technologies: dependency injection, events, resources, i18n, validation, data binding, typeconversion, SpEL, AOP.

2. Testing: mock objects, TestContext framework, Spring MVC Test, WebTestClient.

3. Data Access: transactions, DAO support, JDBC, ORM, Marshalling XML

4. Spring MVC/WebFlux: web frameworks.

5. Integration: remoting, JMS, JCA, JMX, email,tasks, scheduling, cache.

6. Languages: Kotlin, Groovy, dynamic languages.
```

#### Spring AOP

```
1. AOP 面向切面编程：Spring 早期版本的核心功能，管理对象生命周期与对象装配

2、IOC 控制反转（依赖注入）：对象装配的改进，思想是面向接口编程

3、循环依赖问题

```

AOP 的实现方式：中间代理层，字节码增强来实现

```
1、jdk 动态代理

2、cglib 字节码增强

3、字节码增强与反射的区别：一个类似基因操作，一个类似拍CT
```

#### Spring Bean 生命周期

* Spring容器初始化过程：

```
AnnotationConfigApplicationContext(){ 
    refresh() // 创建刷新
}  

AbstractApplicationContext -> refresh()

1、prepareRefresh() 刷新前预处理
    1）initPropertySources() 初始化属性设置，空方法，子类可以自定义个性化属性设置
    2）getEnvironment().validateRequiredProperties() 属性校验
    3）this.earlyApplicationEvents = new LinkedHashSet<ApplicationEvent>(); 保存容器中一些事件
2、obtainFreshBeanFactory(); 获取 BeanFactory
    1）GenericApplicationContext -> refreshBeanFactory(); 刷新bean工程
        // 创建了一个BeanFactory对象
        this.beanFactory = new DefaultListableBeanFactory();
    2）getBeanFactory() 返回 GenericApplicationContext 创建的BeanFactory对象
    3）将创建的BeanFactory【DefaultListableBeanFactory】返回
3、prepareBeanFactory(beanFactory); BeanFactory的预准备工作
    1）设置BeanFactory的类加载器、支持的表达式解析器等
    2）添加后置处理器 BeanPostProcessor【ApplicationContextAwareProcessor】
    3）设置忽略的自动装配的接口
    4）注册可以解析的自动装配，可以在任何组件中自动注入
        BeanFactory、ResourceLoader、ApplicationEventPublisher、ApplicationContext
    5）添加后置处理器 BeanPostProcessor【ApplicationListenerDetector】  
    6）添加编译时的AspectJ
    7）给BeanFactory中注册一些能用的组件 
        environment【ConfigurableEnvironment】  
        systemProperties 【Map<String, Object>】
        systemEnvironment 【Map<String, Object>】'
4、postProcessBeanFactory(beanFactory); BeanFactory准备工作完成后进行的后置处理
    1）子类通过重写这个方法在BeanFactory创建并预准备完成以后做进一步的设置
5、invokeBeanFactoryPostProcessors(beanFactory);执行 BeanFactoryPostProcessors 
    BeanFactoryPostProcessors：BeanFactory的后置处理器，在BeanFactory标准初始化之后执行   
    两个接口：BeanFactoryPostProcessor、BeanDefinitionRegistryPostProcessor   
    1）执行 BeanFactoryPostProcessor 的方法
        1）先执行BeanDefinitionRegistryPostProcessor 
            1）获取所有的 BeanDefinitionRegistryPostProcessor 
            2）先执行实现了PriorityOrdered接口的BeanDefinitionRegistryPostProcessor  优先级排序
               postProcessor.postProcessBeanDefinitionRegistry(registry);
            3）在执行实现了Ordered顺序接口的BeanDefinitionRegistryPostProcessor    
               postProcessor.postProcessBeanDefinitionRegistry(registry);
            4）最后执行没有实现任何优先级或者是顺序接口的 BeanDefinitionRegistryPostProcessors    
               postProcessor.postProcessBeanDefinitionRegistry(registry);
        2）再执行BeanFactoryPostProcessor
            1）获取所有的BeanFactoryPostProcessor
            2）先执行实现了PriorityOrdered接口的BeanFactoryPostProcessor优先级排序
               postProcessor.postProcessBeanFactory(beanFactory);             
            3）在执行实现了Ordered顺序接口的BeanFactoryPostProcessor
               postProcessor.postProcessBeanFactory(beanFactory);             
            4）最后执行没有实现任何优先级或者是顺序接口的BeanFactoryPostProcessor
               postProcessor.postProcessBeanFactory(beanFactory);    
6、registerBeanPostProcessors(beanFactory);注册 BeanPostProcessors（Bean的后置处理器）       
    不同类型的BeanPostProcessor，在Bean创建前后的执行时机是不一样的
    BeanPostProcessor
    DestructionAwareBeanPostProcessor
    InstantiationAwareBeanPostProcessor
    SmartInstantiationAwareBeanPostProcessor                    
    MergedBeanDefinitionPostProcessor【internalPostProcessors】
    
    PostProcessorRegistrationDelegate -> registerBeanPostProcessors
    1）获取所有的BeanPostProcessor；后置处理器默认都有PriorityOrdered、Ordered
    2）先注册PriorityOrdered优先级接口的BeanPostProcessor 
       把每一个BeanPostProcessor添加到BeanFactory
       beanFactory.addBeanPostProcessor(postProcessor);
    3）再注册Ordered接口的BeanPostProcessor
    4）最后注册没有实现任何优先级接口的BeanPostProcessor
    5）最终注册MergedBeanDefinitionPostProcessor
    6）注册一个ApplicationListenerDetector；来在Bean创建完成后检查是否是ApplicationListener
        this.applicationContext.addApplicationListener((ApplicationListener<?>) bean);
7、initMessageSource();初始化MessageSource组件（做国际化功能；消息绑定；消息解析）        
    1）获取BeanFactory 
    2）看容器中是否有id为messageSource的，类型是MessageSource的组件  
        如果有赋值给messageSource，如果没有创建一个默认的DelegatingMessageSource
        MessageSource：取出国际化配置文件中的某个Key的值；能按照区域信息获取；
    3）把创建好的MessageSource注册在容器中，以后获取国际化配置文件的值的时候，可以自动注入 MessageSource
    beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);   
8、initApplicationEventMulticaster();初始化事件派发器
    1）获取BeanFactory
    2）从BeanFactory中获取 applicationEventMulticaster 的 ApplicationEventMulticaster
    3）如果没有配置，创建一个 SimpleApplicationEventMulticaster       
    4）将创建的 ApplicationEventMulticaster 注入到容器中，以后其他组件直接自动注入 
9、onRefresh();留给子容器，重写 onRefresh 方法
10、registerListeners();将项目里面所有的 ApplicationListener 注册进来     
    1）从容器中拿到所有的 ApplicationListener
    2）将每个监听器添加到事件派发器
        getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
    3）派发之前步骤产生的事件
11、finishBeanFactoryInitialization(beanFactory); 初始化所有剩下的单实例Bean
    1）beanFactory.preInstantiateSingletons();【DefaultListableBeanFactory】
        1）获取容器中的所有Bean，依次进行初始化和创建对象
        2）获取Bean的定义信息 RootBeanDefinition
        3）Bean不是抽象，是单实例的，是懒加载的
            1）判断是否是FactoryBean；是否是实现 FactoryBean 接口的Bean
            2）不是工厂Bean。利用 getBean(beanName)创建对象
                0）getBean(beanName)；ioc.getBean()；
                1）doGetBean(name, null, null, false);【AbstractBeanFactory】
                2）先获取缓存中保存的单实例Bean。 getSingleton(beanName);
                   【DefaultSingletonBeanRegistry】private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);
                3）缓存中获取不到，开始Bean的创建对象流程
                4）标记当前Bean已经被创建 markBeanAsCreated(beanName);
                5）获取Bean的定义信息
                6）获取当前Bean依赖的其他Bean
                    String[] dependsOn = mbd.getDependsOn();
                   如果有依赖按照getBean()把依赖的Bean先创建出来 
                7）启动单实例Bean的创建流程
                    1）createBean(beanName, mbd, args);【AbstractAutowireCapableBeanFactory】
                    2）Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
                        让BeanPostProcessor先拦截返回代理对象 
                        InstantiationAwareBeanPostProcessor：提前执行
                            先触发：postProcessBeforeInstantiation()  
                            如果有返回值触发：postProcessAfterInstantiation()
                    3）如果前面的InstantiationAwareBeanPostProcessor没有返回代理对象，执行 4）        
                    4）Object beanInstance = doCreateBean(beanName, mbdToUse, args);        
                        1）【创建Bean实例】：createBeanInstance(beanName, mbd, args);    
                            利用工厂方法或者对象的构造器创建出Bean实例；
                        2）applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
                            调用MergedBeanDefinitionPostProcessor的
                                bdp.postProcessMergedBeanDefinition(mbd, beanType, beanName);
                        3）【Bean属性赋值】：populateBean(beanName, mbd, instanceWrapper);
                            赋值之前：
                            1）拿到InstantiationAwareBeanPostProcessor后置处理器
                                postProcessAfterInstantiation()
                            2）拿到InstantiationAwareBeanPostProcessor后置处理器
                                postProcessPropertyValues()
                            3）应用Bean属性的值：为属性利用setter方法等进行赋值
                                applyPropertyValues(beanName, mbd, bw, pvs);
                        4）【Bean初始化】：initializeBean(beanName, exposedObject, mbd);
                            1）【执行Aware接口方法】invokeAwareMethods(beanName, bean); 执行xxxAware接口方法
                                BeanNameAware、BeanClassLoaderAware、BeanFactoryAware
                            2）【执行后置处理器初始化之前】applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);     
                                BeanPostProcessor.postProcessBeforeInitialization()   
                            3）【执行初始化方法】invokeInitMethods(beanName, wrappedBean, mbd);
                                1）是否是 InitializingBean 接口的实现；执行接口规定的初始化
                                2）是否自定义初始化方法
                            4）【执行后置处理器初始化之后】applyBeanPostProcessorsAfterInitialization    
                                BeanPostProcessor.postProcessAfterInitialization()
                        5）注册Bean的销毁方法：registerDisposableBeanIfNecessary(beanName, bean, mbd);
                    5）将创建的Bean添加到缓存中：addSingleton(beanName, singletonObject);                    
            3）所有Bean利用getBean创建完成后； 检查所有的Bean是否SmartInitializingSingleton的实现
                如果是，执行 afterSingletonsInstantiated()   
12）finishRefresh();完成BeanFactory的初始化创建工作；IOC容器就创建完成     
    1）initLifecycleProcessor(); 初始化和生命周期有关的后置处理器  
       执行 LifecycleProcessor 接口的实现类
       onRefresh()
       onClose()
    2) getLifecycleProcessor().onRefresh();       
        拿到前面定义的生命周期处理器（BeanFactory）回调 onRefresh()
    3）publishEvent(new ContextRefreshedEvent(this)); 发布容器刷新完成事件
    4）LiveBeansView.registerApplicationContext(this);                                  
```

* 总结：

```
1、Spring容器在启动的时候，先会保存所有注册进来的Bean的定义信息；
    1）xml注册的bean
    2）注解注册的bean
2、Spring容器会在合适的时机创建这些bean
    1）用到这个bean的时候，利用getBean创建bean，创建好后保存在容器中
    2）统一创建剩下所有的bean的时候 finishBeanFactoryInitialization(beanFactory); 
3、后置处理器
    1）每一个bean创建完成，都会使用各种后置处理器进行处理，来增强bean的功能
       AutowiredAnnotationBeanPostProcessor：处理自动注入
       AnnotationAwareAspectJAutoProxyCreator：来做AOP功能
       ...     
4、事件驱动模型
    ApplicationListener 事件监听
    ApplicationEventMulticaster：事件派发
           
```

