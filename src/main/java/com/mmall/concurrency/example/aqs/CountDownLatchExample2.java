package com.mmall.concurrency.example.aqs;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

    private static int threadCount=200;

    public static void main(String[] args) throws Exception {
        ExecutorService executor= Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch=new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum=i;
//            Thread.sleep(1);
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

        countDownLatch.await(10,TimeUnit.MILLISECONDS);//不会死等待
        log.info("Finish------------------------------------");
        executor.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
