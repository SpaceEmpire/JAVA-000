package com.liq.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description TODO
 * author: liquan
 * date: 2021/01/05 22:58
 * version: 1.0
 */
@Component
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void sendMess(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
