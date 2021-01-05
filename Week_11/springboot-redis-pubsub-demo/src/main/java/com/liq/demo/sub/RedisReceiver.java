package com.liq.demo.sub;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 处理接收消息
 *
 * @author liquan
 * date: 2021/01/05 23:08
 * version: 1.0
 */
@Component
@Log4j2
public class RedisReceiver {

    /**
     * 接收订阅消息
     *
     * @param message 消息
     */
    public void receiveMessage(String message) {
        log.info("接收消息:" + message);
    }
}
