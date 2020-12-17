package com.liq.rpcfx.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.liq.rpcfx.api.RpcfxRequest;
import com.liq.rpcfx.api.RpcfxResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;


/**
 * RpcfxCglib
 * author: liquan
 * date: 2020/12/17 00:05
 * version: 1.0
 */
public final class RpcfxCglib {

    static {
        ParserConfig.getGlobalInstance().addAccept("com.liq");
    }

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    public static <T> T create(final Class<T> serviceClass, final String url) {
        return (T) Enhancer.create(serviceClass, new MyInterceptor(serviceClass, url));
    }

    private static class MyInterceptor implements MethodInterceptor {

        private final String url;

        private final Class<?> serviceClass;

        public <T> MyInterceptor(Class<?> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        /**
         * @param proxy       被代理对象 com.liq.rpcfx.demo.provider.UserServiceImpl@3b90ec55
         * @param method      代理的方法 public abstract com.liq.rpcfx.demo.api.User com.liq.rpcfx.demo.api.UserService.findById(int)
         * @param params      方法参数
         * @param methodProxy 当前执行方法的代理对象
         * @return
         * @throws Throwable
         */
        @Override
        public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);
            RpcfxResponse response = post(request, url);
            return JSON.parse(response.getResult().toString());
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: " + reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp json: " + respJson);
            return JSON.parseObject(respJson, RpcfxResponse.class);
        }
    }
}
