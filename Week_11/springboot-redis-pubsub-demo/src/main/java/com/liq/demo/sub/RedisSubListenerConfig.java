package com.liq.demo.sub;

import com.liq.demo.config.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * @description TODO
 * author: liquan
 * date: 2021/01/05 23:12
 * version: 1.0
 */
@Component
public class RedisSubListenerConfig {

    /**
     * 初始化监听器
     *
     * @param connectionFactory connectionFactory
     * @param listenerAdapter   listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(Const.CHANNEL));
        return container;
    }

    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param redisReceiver redisReceiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }

    @Bean
    public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    /**
     * 注册订阅者
     *
     * @return receiver
     */
    @Bean
    public RedisReceiver receiver() {
        return new RedisReceiver();
    }
}
