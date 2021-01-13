package com.liq.demo.controller;

import com.liq.demo.bean.User;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费者
 * @author liquan
 * date: 2021/01/13 15:34
 * version: 1.0
 */
@RestController
public class ConsumerController {

    @JmsListener(destination = "amq.queue")
    public void readActiveQueue(User user) {
        System.out.println("接收到queue消息：" + user.toString());
    }

    @JmsListener(destination = "amq.topic")
    public void readActiveTopic(User user) {
        System.out.println("接收到topic消息：" + user.toString());
    }
}
