Week05 作业题目（周六）：

```
4.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
```

#### 自动化配置原理

* 1、开启自动化配置：@EnableAutoConfiguration

```
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

* 2、添加配置类：@Configuration

```
@Configuration
public class WebConfiguration {
    
}
```

* 3、添加自动配置类

```
@Configuration
@Import(WebConfiguration.class)
public class WebAutoConfiguration {
    
}
```

* 4、配置 spring.factories 自动装配

```
// src/main/resources/META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.xx.config.WebAutoConfiguration
```

#### 条件化自动装配


