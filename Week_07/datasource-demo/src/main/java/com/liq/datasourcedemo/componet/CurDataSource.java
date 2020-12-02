package com.liq.datasourcedemo.componet;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * 指定要使用的数据源
 * @author liquan
 * date: 2020/12/01 22:45
 * version: 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurDataSource {

    String name() default "";
}
