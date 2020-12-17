package com.liq.rpcfx.demo.provider;

import com.liq.rpcfx.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final static Map<String, Object> cacheBean = new ConcurrentHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {

        Object object = cacheBean.get(serviceClass);

        try {
            if (object == null) {
                Class xlass = Class.forName(serviceClass);
                object = this.applicationContext.getBean(xlass);
                cacheBean.putIfAbsent(serviceClass, object);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }
}
