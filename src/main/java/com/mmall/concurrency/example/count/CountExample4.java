package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author:zhumeng
 * @desc: 不安全 volatile不具有原子性
 * 1.对变量的些操作不依赖当前值
 * 2.该变量没有包含在具有其他变量的不变式中
 *
 * 1.状态标记量
 * 2.
 **/
@Slf4j
@NotThreadSafe
public class CountExample4 {
    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("count:{}", count);
    }

    public static void add() {
        count++;
        //1.count
        //2.+1
        //3.count
    }

}
