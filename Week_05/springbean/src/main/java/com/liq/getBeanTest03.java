package com.liq;

import com.liq.bean.Student;
import com.liq.config.BeanConfig02;
import com.liq.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 14:52
 * version: 1.0
 */
public class getBeanTest03 {
    public static void main(String[] args) {

        // 组件扫描方式注入bean
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig02.class);
        StudentService studentService = applicationContext.getBean(StudentService.class);

        Student student = new Student();
        student.setName("tony");
        student.setAge(18);
        studentService.addOne(student);

    }
}
