package com.liq.hmilydemo.order.service;

import com.liq.hmilydemo.common.order.entity.Order;

/**
 * 支付操作接口
 * author: liquan
 * date: 2020/12/09 23:19
 * version: 1.0
 */
public interface PaymentService {

    /**
     * 订单支付
     *
     * @param order 订单实体
     */
    void makePayment(Order order);

    /**
     * mock订单支付的时候库存异常.
     *
     * @param order 订单实体
     * @return String string
     */
    String mockPaymentInventoryWithTryException(Order order);
}
