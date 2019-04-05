package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample1 {

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
                }
            });
        }
        executor.shutdown();

    }

    private static void race(int threadNum) throws Exception {

        barrier.await();
        log.info("{} continue",threadNum);
    }
}
