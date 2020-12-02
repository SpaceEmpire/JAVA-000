package com.liq.test;

import com.liq.bean.Order;
import com.liq.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/01 11:09
 * version: 1.0
 */
public class CreateOrderTest {

    /**
     * 创建总数
     */
    private final int totalCount = 1000000;

    /**
     * 批量插入数量
     */
    private int batchSize = 1;

    /**
     * 创建线程数量
     */
    private int threadCount = 1;

    private OrderService orderService;

    public CreateOrderTest(OrderService orderService, int batchSize) {
        this.orderService = orderService;
        this.batchSize = batchSize;
    }

    public CreateOrderTest(OrderService orderService, int threadCount, int batchSize) {
        this.orderService = orderService;
        this.threadCount = threadCount;
        this.batchSize = batchSize;
    }

    /**
     * 添加
     */
    public void addOne() {
        Long beginTime = System.currentTimeMillis();
        for (int i = 0; i < totalCount; i++) {
            Order order = createOrder(batchSize).get(0);
            int result = orderService.addOne(order);
            System.out.println("第：" + i + "条，插入结果：" + result);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("addOne耗时：" + (endTime - beginTime) / 1000 + "秒");
    }

    /**
     * 批量添加
     */
    public void addBatch() {
        Long beginTime = System.currentTimeMillis();

        int count = totalCount / batchSize;
        if (totalCount % batchSize != 0) {
            count++;
        }

        for (int i = 0; i < count; i++) {
            List<Order> list = createOrder(batchSize);
            int result = orderService.addBatch(list);
            System.out.println("第" + i + "批执行结果:" + result);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("addBatch耗时：" + (endTime - beginTime) / 1000 + "秒");
    }

    /**
     * 多线程，批量添加
     */
    public void threadsAddBatch() {
        Long beginTime = System.currentTimeMillis();

        ExecutorService service = Executors.newFixedThreadPool(50);
        final CountDownLatch cdl = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int count = totalCount / threadCount / batchSize;
            service.execute(() -> {
                for (int j = 0; j < count; j++) {
                    List<Order> list = createOrder(batchSize);
                    int result = orderService.addBatch(list);
                    System.out.println("第" + Thread.currentThread().getName() + "个线程，第" + j + "批，执行结果:" + result);
                }
                cdl.countDown();
            });
        }

        service.shutdown();

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("threadsAddBatch 耗时：" + (endTime - beginTime) / 1000 + "秒");
    }

    /**
     * 批量创建订单
     *
     * @param count 创建数量
     * @return 返回order集合
     */
    private List<Order> createOrder(int count) {
        List<Order> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            Order order = new Order();
            order.setOrder_id(UUID.randomUUID().toString());
            order.setCreate_time(Integer.parseInt(System.currentTimeMillis() / 1000 + ""));
            order.setGood_id(1);
            order.setGood_price(1);
            order.setAmount(0);
            order.setCount(0);
            order.setStatus(0);
            list.add(order);
        }
        return list;
    }
}
