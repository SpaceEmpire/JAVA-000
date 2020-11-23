package com.liq;

import com.liq.proxy.StudentAgentInterceptor;
import com.liq.proxy.StudentInvocationHandler;
import com.liq.proxy.StudentProxy;
import com.liq.service.StudentService;
import com.liq.service.impl.StudentServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;


/**
 * @description TODO
 * author: liquan
 * date: 2020/11/23 16:27
 * version: 1.0
 */
public class bytebuddyTest {

    public static void main(String[] args) throws Exception {
        StudentService studentService = createByteBuddyHandler();
        studentService.select();
        System.out.println(studentService.toString());

        // 没有起作用
        studentService = createByteBuddyInterceptor();
        studentService.select();
        System.out.println(studentService.toString());

        // 没有起作用
        studentService = createByteBuddyProxy();
        studentService.select();
        System.out.println(studentService.toString());
    }

    /**
     * 通过适配JDK的InvocationHandler实现方法的拦截
     *
     * @return
     * @throws Exception
     */
    private static StudentService createByteBuddyHandler() throws Exception {

        StudentInvocationHandler stuIh = new StudentInvocationHandler(new StudentServiceImpl());

        Object object = new ByteBuddy().subclass(Object.class)
                .implement(StudentService.class)
                .method(ElementMatchers.named("select"))
                .intercept(InvocationHandlerAdapter.of(stuIh))
                .make()
                .load(bytebuddyTest.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();

        return (StudentService) object;
    }

    /**
     * 通过方法委托（MethodDelegation）的方式实现拦截器
     *
     * @return
     * @throws Exception
     */
    private static StudentService createByteBuddyInterceptor() throws Exception {

        StudentAgentInterceptor stuIh = new StudentAgentInterceptor(new StudentServiceImpl());

        Object object = new ByteBuddy().subclass(Object.class)
                .implement(StudentService.class)
                .method(ElementMatchers.named("select"))
                .intercept(MethodDelegation.to(stuIh))
                .make()
                .load(bytebuddyTest.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
        return (StudentService) object;
    }

    /**
     * 通过代理类实现
     *
     * @return
     * @throws Exception
     */
    private static StudentService createByteBuddyProxy() throws Exception {
        return (StudentService) new ByteBuddy().subclass(StudentServiceImpl.class)
                .implement(StudentService.class)
                .method(ElementMatchers.named("select"))
                .intercept(MethodDelegation.to(new StudentProxy()))
                .make()
                .load(bytebuddyTest.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

}


