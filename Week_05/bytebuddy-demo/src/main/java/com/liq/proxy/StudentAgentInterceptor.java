package com.liq.proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description 通过方法委托（MethodDelegation）的方式实现拦截器
 * author: liquan
 * date: 2020/11/23 17:01
 * version: 1.0
 */
public class StudentAgentInterceptor {

    private Object delegate;

    public StudentAgentInterceptor(Object delegate) {
        this.delegate = delegate;
    }

    /**
     * @This 表示生成的代理对象
     * @Origin 表示被代理的方法
     * @AllArguments 表示方法参数
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Exception
     */
    public Object interceptor(@This Object proxy, @Origin Method method, @AllArguments Object args) throws Exception {
        before();
        Object ret = method.invoke(delegate, args);
        after();
        return ret;
    }

    /**
     * 调用方法之前执行
     */
    private void before() {
        System.out.println(String.format("log before time [%s] ", new Date()));
    }

    /**
     * 调用方法之后执行
     */
    private void after() {
        System.out.println(String.format("log after time [%s] ", new Date()));
    }
}
