package com.liq.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/17 22:30
 * version: 1.0
 */
public class StudentBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    public StudentBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().add("age", element.getAttribute("age"));
        beanDefinition.getPropertyValues().add("name", element.getAttribute("name"));
        BeanDefinitionRegistry beanDefinitionRegistry = parserContext.getRegistry();
        //注册bean到BeanDefinitionRegistry中
        beanDefinitionRegistry.registerBeanDefinition(beanClass.getName(), beanDefinition);
        return beanDefinition;
    }
}
