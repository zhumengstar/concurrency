package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample2 {

//    private static int threadCount=200;
    private static CyclicBarrier barrier=new CyclicBarrier(5);
    //多少线程进行同步等待

    public static void main(String[] args) throws Exception {
        ExecutorService executor= Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum=i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    log.error("execption",e);
                    //BrokenBarrierException
                }
            });
        }
        executor.shutdown();

    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try {
            barrier.await(2000,TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException e){
            log.warn("BrokenBarrierException");
        }catch (TimeoutException e){
            log.warn("TimeoutException");
        }
        log.info("{} continue",threadNum);
    }
}
