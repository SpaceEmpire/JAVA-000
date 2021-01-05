package com.liq.redission.demo.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/31 18:51
 * version: 1.0
 */
@RestController
public class TestController {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() throws InterruptedException {
        String key = "key_1";
        RLock rLock = redissonClient.getLock(key);
        try {
            // 加锁
            rLock.lock(5000, TimeUnit.SECONDS);
            Thread.sleep(10000);
        } catch (Exception e) {
            // 模拟执行任务
            Thread.sleep(1000 * 6);
        } finally {
            // 释放锁
            rLock.unlock();
        }
        return true;
    }
}
