package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author:zhumeng
 * @desc: StringBuilder 线程封闭 线程不安全
 * StringBuffer 线程安全 Synchronized
 **/
@Slf4j
@NotThreadSafe
@ThreadSafe
public class  StringExample1 {
    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();
    public static StringBuffer stringBuffer = new StringBuffer();

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
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
//        System.out.println(count);
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("stringBuilder size:{}", stringBuilder.length());
        log.info("stringBuffer size:{}", stringBuffer.length());
    }

    public static void update() {
        stringBuilder.append("1");
        stringBuffer.append("1");
//        System.out.println(stringBuffer.length());
    }

}
