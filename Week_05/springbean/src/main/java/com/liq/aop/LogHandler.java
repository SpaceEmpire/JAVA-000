package com.liq.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 22:34
 * version: 1.0
 */
public class LogHandler implements InvocationHandler {

    private Object target;

    public Object getProxyInstance(Object target) {
        this.target = target;
        Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
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
