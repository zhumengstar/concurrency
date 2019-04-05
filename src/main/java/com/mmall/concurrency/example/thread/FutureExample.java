package com.mmall.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String >{
        @Override
        public String call() throws InterruptedException {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            ExecutorService executor= Executors.newCachedThreadPool();
            Future<String > future=executor.submit(new MyCallable());
            log.info("do something in main");
            Thread.sleep(5000);
            String result=future.get();
            log.info("result:{}",result);
        }
    }
}
