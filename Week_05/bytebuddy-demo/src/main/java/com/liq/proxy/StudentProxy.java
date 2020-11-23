package com.liq.proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description 通过代理类实现
 * author: liquan
 * date: 2020/11/23 17:01
 * version: 1.0
 */
public class StudentProxy {


    /**
     * @SuperMethod 表示父类的方法
     *
     * @param proxy
     * @param method
     * @param superMethod
     * @param args
     * @return
     * @throws Exception
     */
    public Object interceptor(@This Object proxy, @Origin Method method, @SuperMethod Method superMethod, @AllArguments Object args) throws Exception {
        before();
        Object ret = method.invoke(proxy, args);
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
