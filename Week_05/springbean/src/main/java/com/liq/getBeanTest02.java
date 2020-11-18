package com.liq;

import com.liq.bean.Student;
import com.liq.config.BeanConfig01;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 14:52
 * version: 1.0
 */

@ComponentScan("")
public class getBeanTest02 {
    public static void main(String[] args) {

        // 配置类方式注入bean
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig01.class);
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.toString());

    }
}
