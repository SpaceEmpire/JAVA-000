package com.liq.demo.controller;

import com.liq.demo.utils.RedisUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/31 18:51
 * version: 1.0
 */
@RestController
@Log4j2
public class TestLockController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/lock", method = RequestMethod.GET)
    public Object lock() throws InterruptedException {

        Jedis jedis = jedisPool.getResource();

        String lockKey = "lockKey";

        String requestId = UUID.randomUUID().toString();

        try {
            Boolean result = RedisUtil.tryLock(jedis, lockKey, requestId, 100L);
            log.info("tryLock:" + result);
        } catch (Exception e) {
            // 模拟执行任务
            Thread.sleep(1000 * 6);
        } finally {
            Thread.sleep(10000);
            RedisUtil.unlock(jedis, lockKey, requestId);
            RedisUtil.returnResource(jedisPool, jedis);
        }

        return 1;
    }
}
