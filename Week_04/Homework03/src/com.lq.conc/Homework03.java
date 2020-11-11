package com.gudaoxuan.conc;

import sun.misc.Lock;

import java.sql.SQLOutput;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03 {


    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        int result = 0;
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        // 方式1、Thread.sleep()
//        MyRunable myRunable = new MyRunable();
//        new Thread(myRunable).start();
//        Thread.sleep(100);
//        result = myRunable.getResult();

        // 方式2、CountDownLatch
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        MyRunable02 myRunable2 = new MyRunable02(countDownLatch);
//        new Thread(myRunable2).start();
//        countDownLatch.await();
//        result = myRunable2.getResult();

        // 方式3、Future
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        MyCallable myCallable = new MyCallable();
//        Future<Integer> future = es.submit(myCallable);
//        result = future.get();
//        es.shutdown();

        // 方式4、FutureTask
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(myCallable);
//        es.submit(futureTask);
//        result = futureTask.get();
//        es.shutdown();

        // 方式5、thread.join()
//        MyRunable myRunable = new MyRunable();
//        Thread thread = new Thread(myRunable);
//        thread.start();
//        thread.join();
//        result = myRunable.getResult();

        // 方式6、CyclicBarrier
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
//        MyCyclicBarrier myCyclicBarrier = new MyCyclicBarrier(cyclicBarrier);
//        new Thread(myCyclicBarrier).start();
//        cyclicBarrier.await();
//        result = myCyclicBarrier.getResult();

        // 方式7、Thread.yield()
//        MyResult myResult = new MyResult();
//        new Thread(() -> {
//            System.out.println("1、当前活动线程数量：" + Thread.activeCount());
//            myResult.sum();
//        }).start();
//        System.out.println("2、当前活动线程数量：" + Thread.activeCount());
//
//        //保证前面的线程都执行完
//        while (Thread.activeCount() > 2)
//            // 线程让步，并不保证
//            Thread.yield();
//        System.out.println("3、当前活动线程数量：" + Thread.activeCount());
//        result = myResult.getResult();

        // 方式8，volatile
//        MyRunable03 myRunable03 = new MyRunable03();
//        new Thread(myRunable03).start();
//
//        while (myRunable03.getResult() == 0) {
//        }
//        result = myRunable03.getResult();

        // 方式9、CompletableFuture
//        result = CompletableFuture.supplyAsync(() -> {
//            return Homework03.sum();
//        }).join();

        // 方式10、LockSupport
        MyRunable myRunable = new MyRunable();
        new Thread(myRunable).start();
        LockSupport.parkNanos(1000000000L);
        result = myRunable.getResult();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    static class MyRunable implements Runnable {

        private int result = 0;

        @Override
        public void run() {
            result = Homework03.sum();
        }

        public int getResult() {
            return result;
        }
    }

    static class MyRunable02 implements Runnable {

        private int result = 0;

        private CountDownLatch countDownLatch;

        public MyRunable02(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                result = Homework03.sum();
            } finally {
                countDownLatch.countDown();
            }
        }

        public int getResult() {
            return result;
        }
    }

    static class MyRunable03 implements Runnable {

        private volatile int result = 0;

        @Override
        public void run() {
            result = Homework03.sum();
        }

        public int getResult() {
            return result;
        }
    }

    static class MyRunable04 implements Runnable {

        private int result = 0;

        @Override
        public void run() {
            result = Homework03.sum();
        }

        public int getResult() {
            return result;
        }
    }

    static class MyResult {

        private int result = 0;

        public void sum() {
            result = Homework03.sum();
        }

        public int getResult() {
            return result;
        }
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return Homework03.sum();
        }
    }

    static class MyCyclicBarrier implements Runnable {

        private int result = 0;

        private CyclicBarrier cyclicBarrier;

        public MyCyclicBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                result = Homework03.sum();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public int getResult() {
            return result;
        }
    }
}
