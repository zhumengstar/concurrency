package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {

    private static int threadCount=200;

    public static void main(String[] args) throws Exception {
        ExecutorService executor= Executors.newCachedThreadPool();

        Semaphore semaphore=new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum=i;
            executor.execute(()->{

                    try {
                        semaphore.acquire(3);
                        test(threadNum);
                        semaphore.release(3);
                    } catch (InterruptedException e) {
                        log.error("execption");
                    }
            });

        }
        log.info("Finish");
        executor.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {

        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
