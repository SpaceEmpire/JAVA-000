package com.liq.demo.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 消费者
 *
 * @author liquan
 * date: 2021/01/13 15:34
 * version: 1.0
 */
@RestController
public class ConsumerController {

//    @KafkaListener(id = "webGroup",topics = "testk")
//    public void listen(String t){
//        System.out.println(t);
//    }

    /**
     * KafkaListener中可以指定监听多个主题，也可以指定组名，
     * 如果在此处和application.properties中同时指定了组名，将以此处为准
     *
     * @param record
     */
    @KafkaListener(groupId = "group1", topics = {"test02"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println(record);
            System.out.println(message);
        }
    }
}
