package com.liq.demo.service.impl;

import com.liq.demo.config.Const;
import com.liq.demo.config.RedisService;
import com.liq.demo.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发布消息
 * author: liquan
 * date: 2021/01/05 23:03
 * version: 1.0
 */
@Service
public class PubServiceImpl implements PubService {

    @Autowired
    private RedisService redisService;

    /**
     * 发布消息
     *
     * @param msg 消息
     * @return success
     */
    @Override
    public String pushMsg(String msg) {
        redisService.sendMess(Const.CHANNEL, msg);
        return "success";
    }
}
