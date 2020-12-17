package com.liq.rpcfx.api;

/**
 * 封装RPC返回结果
 *
 * @author liquan
 * date: 2020/12/16 00:16
 * version: 1.0
 */
public class RpcfxResponse {

    private Object result;

    private boolean status;

    private Exception exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
