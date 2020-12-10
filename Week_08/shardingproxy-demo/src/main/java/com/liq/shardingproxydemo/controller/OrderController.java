package com.liq.shardingproxydemo.controller;

import com.liq.shardingproxydemo.bean.Order;
import com.liq.shardingproxydemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/10 09:10
 * version: 1.0
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired(required = false)
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/find")
    public List<Order> findByOrderId(@RequestParam Long orderId) {
        return orderService.findByOrderId(orderId);
    }

    @RequestMapping("/add")
    public Boolean addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @RequestMapping("/del")
    public Boolean deleteByOrderId(@RequestParam Integer orderId) {
        return orderService.deleteByOrderId(orderId);
    }


    @RequestMapping("/update")
    public Boolean updateOrderId(@RequestBody Order order) {
        return orderService.updateOrderId(order);
    }
}
