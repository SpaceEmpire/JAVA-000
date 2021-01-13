package com.liq.demo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * JmsConfig
 * @author liquan
 * date: 2021/01/13 15:24
 * version: 1.0
 */
@Configuration
public class JmsConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("amq.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("amq.topic");
    }
}
