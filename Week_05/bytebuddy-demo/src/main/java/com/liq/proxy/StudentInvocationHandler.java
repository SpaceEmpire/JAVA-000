package com.liq.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description 通过适配JDK的InvocationHandler实现方法的拦截
 * author: liquan
 * date: 2020/11/23 16:22
 * version: 1.0
 */
public class StudentInvocationHandler implements InvocationHandler {

    private Object delegate;

    public StudentInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        before();
        Object ret = method.invoke(delegate, objects);
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
