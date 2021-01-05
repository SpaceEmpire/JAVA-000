package com.liq.demo.service;

/**
 * 获取库存回调
 *
 * @author liquan
 * date: 2021/01/05 18:34
 * version: 1.0
 */
public interface IStockCallback {

    /**
     * 获取库存
     *
     * @return 库存
     */
    int getStock();
}
