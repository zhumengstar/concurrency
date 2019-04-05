package com.mmall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(5);

//        for (int i = 0; i < 10; i++) {
//            final int index=i;
//            executorService.execute(() -> log.info("task:{}",index));
//        }
//        executorService.schedule(() -> log.warn("schedule run"),3,TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(() -> log.warn("schedule run"),1,3,TimeUnit.SECONDS);//不停执行
//        executorService.shutdown();

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer tun");
            }
        },new Date(),5*1000);//间隔五秒
    }
}
