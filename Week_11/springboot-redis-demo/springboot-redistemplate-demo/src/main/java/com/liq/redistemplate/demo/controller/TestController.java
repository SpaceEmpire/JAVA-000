package com.liq.redistemplate.demo.controller;

import com.liq.redistemplate.demo.utils.RedisUtil;
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
        redisUtil.set("keys", "124", 0);
        return redisUtil.get("keys", 0);
    }
}
