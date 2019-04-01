package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:zhumeng
 * @desc: AtomicInteger
 **/
@Slf4j
@ThreadSafe
public class CountExample2 {
    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

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
        log.info("count:{}", count.get());
    }

    public static void add() {
        count.incrementAndGet();
        //unsafe.getAndAddInt(this, valueOffset, 1) + 1;
        /**
         * public final int getAndAddInt(Object var1, long var2, int var4) {
         *         int var5;
         *         do {
         *             var5 = this.getIntVolatile(var1, var2);
         *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
         *
         *         return var5;
         *     }
         */

//        count.getAndIncrement();
    }

}
