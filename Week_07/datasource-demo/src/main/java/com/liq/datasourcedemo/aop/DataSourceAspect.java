package com.liq.datasourcedemo.aop;

import com.liq.datasourcedemo.datasource.DataSourceNames;
import com.liq.datasourcedemo.componet.CurDataSource;
import com.liq.datasourcedemo.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 * @author liquan
 * date: 2020/12/02 11:55
 * version: 1.0
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    @Pointcut("@annotation(com.liq.datasourcedemo.componet.CurDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        CurDataSource cds = method.getAnnotation(CurDataSource.class);

        if (cds == null) {
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
            log.debug("set datasource is " + DataSourceNames.FIRST);
        } else {
            DynamicDataSource.setDataSource(cds.name());
            log.debug("set datasource is " + cds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
