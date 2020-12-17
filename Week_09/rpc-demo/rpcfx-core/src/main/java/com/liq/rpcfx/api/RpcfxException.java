package com.liq.rpcfx.api;

/**
 * 自定义RPC异常
 *
 * @author liquan
 * date: 2020/12/16 23:45
 * version: 1.0
 */
public class RpcfxException extends RuntimeException {

    public RpcfxException() {
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcfxException(Throwable cause) {
        super(cause);
    }
}
