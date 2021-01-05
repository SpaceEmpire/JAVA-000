package com.liq.demo.service;

/**
 * 发送消息
 *
 * @author liquan
 * date: 2021/01/05 23:01
 * version: 1.0
 */
public interface PubService {

    /**
     * 发送消息
     *
     * @param msg 消息
     * @return success
     */
    String pushMsg(String msg);
}
