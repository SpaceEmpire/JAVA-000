package com.liq.rpcfx.api;

/**
 * 封装RPC服务请求参数
 *
 * @author liquan
 * date: 2020/12/15 23:51
 * version: 1.0
 */
public class RpcfxRequest {

    /**
     * 请求的类
     */
    private String serviceClass;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private Object[] params;

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
