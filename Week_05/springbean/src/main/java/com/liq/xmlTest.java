package com.liq;

import com.liq.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description 测试自定义 Spring XML
 * author: liquan
 * date: 2020/11/17 22:49
 * version: 1.0
 */
public class xmlTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("student.xml");
        Student student = (Student) ac.getBean(Student.class.getName());
        System.out.println(student.getAge() + " " + student.getName());
    }
}
