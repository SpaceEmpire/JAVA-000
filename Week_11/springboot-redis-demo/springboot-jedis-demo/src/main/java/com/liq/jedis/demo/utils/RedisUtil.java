package com.liq.jedis.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 工具类
 *
 * @author liquan
 * date: 2020/12/31 18:41
 * version: 1.0
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 通过key 获取 value
     *
     * @param key     key
     * @param indexDb redis 库
     * @return 返回 value
     */
    public String get(String key, int indexDb) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            value = jedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 向redis存入key和value,并释放连接资源
     * 如果key已经存在 则覆盖
     *
     * @param key     key
     * @param value   value
     * @param indexDb redis 库
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value, int indexDb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 删除指定的key，也可以传入包含key的数组
     *
     * @param indexDb 选择redis库 0-15
     * @param keys    keys 一个key 也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public Long del(int indexDb, String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            return jedis.del(keys);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 通过key像指定的value值追加值
     *
     * @param key     key
     * @param str     str
     * @param indexDb 选择redis库 0-15
     * @return 成功返回 添加后value的长度 失败 返回 添加的 value 的长度 异常返回0L
     */
    public Long append(String key, String str, int indexDb) {
        Jedis jedis = null;
        Long res = 0L;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            res = jedis.append(key, str);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }

    /**
     * 判断key是否存在
     *
     * @param key     key
     * @param indexDb 选择redis库 0-15
     * @return true OR false
     */
    public Boolean exists(String key, int indexDb) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            return jedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 清空当前数据库所有的key
     *
     * @param indexDb 选择redis库 0-15
     * @return 总是返回OK
     */
    public String flushDb(int indexDb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            return jedis.flushDB();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }

    /**
     * 为key 设置生存时间，当key过期时（生存时间为0），它会自动被删除
     *
     * @param key     key
     * @param value   过期时间，单位：秒
     * @param indexDb 选择redis库 0-15
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    public Long expire(String key, int value, int indexDb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            return jedis.expire(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 以秒为单位，返回给定key的剩余时间
     *
     * @param key     key
     * @param indexDb 选择redis库 0-15
     * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key的剩余生存时间。 发生异常 返回 0
     */
    public Long ttl(String key, int indexDb) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 返回连接池
     *
     * @param jedisPool 连接池
     * @param jedis     实例
     */
    private static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
