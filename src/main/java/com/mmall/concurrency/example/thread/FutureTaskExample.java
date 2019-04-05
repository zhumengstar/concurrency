package com.mmall.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {
//    static class MyCallable implements Callable<String >{
//        @Override
//        public String call() throws InterruptedException {
//            log.info("do something in callable");
//            Thread.sleep(5000);
//            return "Done";
//        }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do something in callable");
            Thread.sleep(1000);
            return "Done";
        });
        new Thread(futureTask).start();
        Thread.sleep(1000);
        log.info("do something in main");
        String result = futureTask.get();
        log.info("result:{}", result);

    }
}

