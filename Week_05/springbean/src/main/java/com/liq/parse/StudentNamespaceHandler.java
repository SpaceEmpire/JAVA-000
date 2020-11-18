package com.liq.parse;

import com.liq.bean.Student;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/17 22:32
 * version: 1.0
 */
public class StudentNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("student", new StudentBeanDefinitionParser(Student.class));
    }
}
