package com.liq.demo.service.impl;

import com.liq.demo.service.IStockCallback;
import com.liq.demo.service.StockService;
import com.liq.demo.utils.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2021/01/05 18:38
 * version: 1.0
 */
@Service
@Log4j2
public class StockServiceImpl implements StockService {

    /**
     * 库存不足
     */
    private static final int LOW_STOCK = 0;

    /**
     * 不限库存
     */
    public static final long UNINITIALIZED_STOCK = -3L;


    /**
     * 执行减库存LUA脚本
     */
    public static final String STOCK_LUA;

    @Autowired
    private JedisPool jedisPool;

    static {
        /**
         * 减库存LUA脚本
         * stock：-1 不限库存，0 没有库存，大于0 剩余库存
         * return：-3:库存未初始化，-2:库存不足，-1:不限库存，大于等于0:剩余库存（扣减之后剩余的库存）
         */
        StringBuilder sb = new StringBuilder();
        sb.append("if (redis.call('exists', KEYS[1]) == 1) then");
        sb.append("    local stock = tonumber(redis.call('get', KEYS[1]));");
        sb.append("    local num = tonumber(ARGV[1]);");
        sb.append("    if (stock == -1) then");
        sb.append("        return -1;");
        sb.append("    end;");
        sb.append("    if (stock >= num) then");
        sb.append("        return redis.call('incrby', KEYS[1], 0 - num);");
        sb.append("    end;");
        sb.append("    return -2;");
        sb.append("end;");
        sb.append("return -3;");
        STOCK_LUA = sb.toString();
    }

    /**
     * 减库存
     *
     * @param key           key
     * @param requestId     请求id
     * @param num           减库存数量
     * @param expire        库存过期时间
     * @param stockCallback 初始化库存回调函数
     * @return -2:库存不足; -1:不限库存; 大于等于0:扣减库存之后的剩余库存
     */
    @Override
    public Long stock(String key, String requestId, Long num, Long expire, IStockCallback stockCallback) {
        Long stock = stock(key, num);
        String lockKey = key + "_lock";
        Long lockTimeOut = 10L;
        // 初始化库存
        if (stock == UNINITIALIZED_STOCK) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                // 获取锁
                if (RedisUtil.tryLock(jedis, lockKey, requestId, lockTimeOut)) {
                    stock = stock(key, num);
                    if (stock == UNINITIALIZED_STOCK) {
                        final int initStock = stockCallback.getStock();
                        // 将库存设置到redis
                        jedis.set(key, String.valueOf(initStock), RedisUtil.SET_IF_NOT_EXIST, RedisUtil.SET_WITH_EXPIRE_TIME, expire);
                        // 调一次扣库存的操做
                        stock = stock(key, num);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } finally {
                RedisUtil.unlock(jedis, lockKey, requestId);
                RedisUtil.returnResource(jedisPool, jedis);
            }
        }
        return stock;
    }

    /**
     * 扣库存
     *
     * @param key key
     * @param num 减库存数量
     * @return 扣减之后剩余的库存 -3:库存未初始化; -2:库存不足; -1:不限库存; 大于等于0:扣减库存之后的剩余库存
     */
    private Long stock(String key, Long num) {
        Jedis jedis = null;
        try {
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(String.valueOf(num));
            jedis = jedisPool.getResource();
            Long result = (Long) jedis.eval(STOCK_LUA, keys, args);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("扣库存失败");
            return -3L;
        } finally {
            RedisUtil.returnResource(jedisPool, jedis);
        }
    }

    /**
     * 获取库存
     *
     * @param key 库存key
     * @return 0:库存不足; -1:库存未初始化; 大于0:剩余库存
     */
    @Override
    public int getStock(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String stock = jedis.get(key);
            return stock == null ? -1 : Integer.parseInt(stock);
        } finally {
            RedisUtil.returnResource(jedisPool, jedis);
        }
    }
}
