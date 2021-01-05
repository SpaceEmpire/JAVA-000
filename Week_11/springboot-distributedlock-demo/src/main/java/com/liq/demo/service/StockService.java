package com.liq.demo.service;

/**
 * 扣库存
 *
 * @author liquan
 * date: 2021/01/05 18:31
 * version: 1.0
 */
public interface StockService {

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
    Long stock(String key, String requestId, Long num, Long expire, IStockCallback stockCallback);


    int getStock(String key);

}
