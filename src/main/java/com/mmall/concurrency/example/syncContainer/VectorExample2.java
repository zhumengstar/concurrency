package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

@Slf4j
@NotThreadSafe//Vector线程可能不安全
public class VectorExample2 {
    private static Vector<Integer> vector=new Vector<>();

    public static void main(String[] args) {
        int num=1000;
        while (num!=0){
            num--;
            for(int i=0;i<10;i++){
                vector.add(i);
            }


            Thread thread1=new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };
            Thread thread2=new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }

    }
}
