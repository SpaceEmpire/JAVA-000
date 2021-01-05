package com.liq.demo.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * @author liquan
 * date: 2021/01/04 23:16
 * version: 1.0
 * @description TODO
 */

public class RedisUtil {

    private static final String LOCK_SUCCESS = "OK";

    public static final String SET_IF_NOT_EXIST = "NX";

    public static final String SET_WITH_EXPIRE_TIME = "EX";

    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超时时间
     * @return true 获取锁成功，false 获取锁失败
     */
    public static boolean tryLock(Jedis jedis, String lockKey, String requestId, Long expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return true 释放锁成功，false 释放锁失败
     */
    public static boolean unlock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return" +
                " redis.call('del',KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 返回连接池
     *
     * @param jedisPool 连接池
     * @param jedis     实例
     */
    public static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
