package com.liq.demo.controller;

import com.liq.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 生产者
 * @author liquan
 * date: 2021/01/13 15:33
 * version: 1.0
 */
@RestController
public class ProducerController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @GetMapping("/sendQueueMsg")
    public void sendQueueMsg(User user) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, user);
    }

    @GetMapping("/sendTopicMsg")
    public void sendTopicMsg(User user) {
        this.jmsMessagingTemplate.convertAndSend(this.topic, user);
    }

}
