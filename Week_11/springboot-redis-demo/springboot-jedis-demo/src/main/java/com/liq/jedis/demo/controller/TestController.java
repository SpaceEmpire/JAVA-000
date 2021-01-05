package com.liq.jedis.demo.controller;

import com.liq.jedis.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/31 18:51
 * version: 1.0
 */
@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() throws InterruptedException {
        String val = redisUtil.get("keys", 2);
        String result = redisUtil.set("key", "123", 0);
//        Long delResult = redisUtil.del(0, "key");
        Long delResult = redisUtil.append("key", "456", 0);
        System.out.println(delResult);

        Boolean b = redisUtil.exists("keys", 0);
        System.out.println(b);
//        System.out.printf(redisUtil.flushDb(2));
        System.out.println(redisUtil.expire("key", 100, 0));
//        Thread.sleep(1000);
        System.out.println(redisUtil.ttl("keys", 0));
        return val;
    }
}
