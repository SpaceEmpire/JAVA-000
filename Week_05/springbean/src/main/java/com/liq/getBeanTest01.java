package com.liq;

import com.liq.bean.Student;
import com.liq.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 14:52
 * version: 1.0
 */
public class getBeanTest01 {
    public static void main(String[] args) {

        // XML 配置方式获取bean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

//        Student student = (Student) applicationContext.getBean("student");
//        System.out.println(student.toString());
//
//        StudentService students02Service = (StudentService) applicationContext.getBean("students02Service");
//        students02Service.addOne(student);
//
//        StudentService students03Service = (StudentService) applicationContext.getBean("students03Service");
//        students03Service.addOne(student);

        applicationContext = new ClassPathXmlApplicationContext("applicationContext02.xml");
        StudentService studentService =(StudentService) applicationContext.getBean("studentServiceImpl");
        studentService.addOne(new Student());

    }
}
