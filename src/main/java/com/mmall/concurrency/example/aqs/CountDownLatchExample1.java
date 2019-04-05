package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class CountDownLatchExample1 {

    private static int threadCount=200;

    public static void main(String[] args) throws Exception {
        ExecutorService executor= Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch=new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum=i;
            executor.execute(()->{

                    try {
                        test(threadNum);
                    } catch (InterruptedException e) {
                        log.error("execption");
                    }finally {
                        countDownLatch.countDown();
                    }

            });

        }

        countDownLatch.await();
        log.info("Finish");
        executor.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",threadNum);
        Thread.sleep(100);
    }
}
