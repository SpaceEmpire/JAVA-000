package com.liq;

import com.apple.laf.AquaButtonBorder;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/23 14:08
 * version: 1.0
 */
public class helloworld {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {


//        testHello();

        testFileClass();
    }

    private static void testHello() throws IllegalAccessException, InstantiationException {
        String helloWorld = new ByteBuddy()// 创建ByteBuddy对象
                .subclass(Object.class)// subclass增强方式
                .method(ElementMatchers.named("toString"))// 拦截其中的toString()方法
                .intercept(FixedValue.value("Hello World!"))// 让toString()方法返回固定值
                .make()
                .load(ByteBuddy.class.getClassLoader())// 加载新类型，默认WRAPPER策略
                .getLoaded()
                .newInstance()// 通过 Java反射创建实例
                .toString();// 调用 toString()方法
        System.out.println(helloWorld);
    }

    private static void testFileClass() {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("com.liq.bean.HelloWrold")
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
                .withParameters(String[].class)
                .intercept(FixedValue.value("Hello World!"))
                .make();
        outputClass(dynamicType.getBytes());
    }

    private static void outputClass(byte[] bytes) {
        FileOutputStream out = null;
        try {
//            String pathName = helloworld.class.getResource("/").getPath() + "ByteBuddyHelloWorld.class";
            String pathName = "./target/classes/HelloWrold.class";
            out = new FileOutputStream(new File(pathName));
            System.out.println("类输出路径：" + pathName);
            out.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
