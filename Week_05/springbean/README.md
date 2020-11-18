Week05 作业题目（周四）：

```
1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。
2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 Github。
3.（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。
```


### 一、Java 里的动态代理，实现一个简单的 AOP

参考：https://segmentfault.com/a/1190000011291179


### 二、Spring Bean 的装配方式

#### 1、applicationContext.xml

```
1) <bean id="student" class="Student"/>

2) 构造器方式注入bean

3) set方法进行注入

4) <context:component-scan base-package="liq.ioc"></context:component-scan>
```

#### 2、注解装配

#### 3、配置类装配

```

@Configuration
public class BeanConfig01 {

    @Bean(name = "student")
    public Student getStudent() {
        return new Student();
    }
}

或

@ComponentScan(value = "liq.ioc")
public class BeanConfig02 {
    
}
```

### 三、Spring XML 自定义

1、定义xsd文件 student.xsd

```
// xmlns 和 targetNamespace 需要相同
// xsd:element定义的就是将来会在xml文件中用到的元素
// xsd:attribute 定义模型属性
<xsd:schema
        xmlns="http://liq.xml.com/school/schema/student"
        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
        xmlns:beans="http://www.springframework.org/schema/beans"
        targetNamespace="http://liq.xml.com/school/schema/student">
    <xsd:import namespace="http://www.springframework.org/schema/beans"/>
    <xsd:element name="student">
        <xsd:complexType>
            <xsd:complexContent>
                <xsd:extension base="beans:identifiedType">
                    <xsd:attribute name="age" type="xsd:int"/>
                    <xsd:attribute name="name" type="xsd:string"/>
                </xsd:extension>
            </xsd:complexContent>
        </xsd:complexType>
    </xsd:element>
</xsd:schema>
```

2、编写spring.schemas，用来指定xsd文件的位置

```
http\://liq.xml.com/school/schema/student.xsd=META-INF/student.xsd
```

3、编写BeanDefinition解析器，主要用来解析自定义的xml标签

4、编写命名空间处理器，用来注册BeanDefinition解析器，通常为每一个xsd:element都要注册一个BeanDefinitionParser

5、编写spring.handlers文件，用于关联命名空间处理器和xsd中的targetNamespace

```
http\://liq.xml.com/school/schema/student=liq.xml.parse.StudentNamespaceHandler
```
