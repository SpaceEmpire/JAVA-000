package com.liq;

import com.liq.aop.LogHandler;
import com.liq.aop.LogInterceptoer;
import com.liq.service.StudentService;
import com.liq.service.impl.StudentServiceImpl;


/**
 * @description  Java动态代理测试
 * author: liquan
 * date: 2020/11/16 22:40
 * version: 1.0
 */
public class aopTest {

    public static void main(String[] args) {

        jdkProxyTest();

        cglibTest();
    }

    /**
     * jdk 动态代理
     */
    private static void jdkProxyTest() {
        StudentService studentService = new StudentServiceImpl();
        System.out.println(studentService.getClass());
        studentService.select();

        LogHandler logHandler = new LogHandler();
        studentService = (StudentService) logHandler.getProxyInstance(studentService);
        System.out.println(studentService.getClass());
        studentService.select();
    }

    /**
     * cglib 动态代理
     */
    private static void cglibTest() {
        StudentService studentsService = new StudentServiceImpl();
        System.out.println(studentsService.getClass());
        studentsService.select();

        studentsService = (StudentServiceImpl) new LogInterceptoer(studentsService).getProxyInstance();
        System.out.println(studentsService.getClass());
        studentsService.select();

    }
}
