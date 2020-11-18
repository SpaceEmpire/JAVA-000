package com.liq.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/17 14:39
 * version: 1.0
 */
public class LogInterceptoer implements MethodInterceptor {

    // 目标对象
    private Object target;

    public LogInterceptoer(Object target) {
        this.target = target;
    }

    /**
     * 为目标对象生成代理对象
     *
     * @return
     */
    public Object getProxyInstance() {
        // 工具类
        Enhancer en = new Enhancer();
        // 设置父类
        en.setSuperclass(target.getClass());
        // 设置回调函数
        en.setCallback(this);
        // 创建子类对象代理
        return en.create();

    }

    /**
     * @param o           要进行增强的对象
     * @param method      拦截的方法
     * @param objects     参数列表
     * @param methodProxy 方法的代理，invokeSuper方法表示对被代理对象方法的调用
     * @return 执行结果
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        methodProxy.invokeSuper(o, objects);
        after();
        return null;
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
