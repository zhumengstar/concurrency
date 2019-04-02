package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式，单线程使用
 */
@NotThreadSafe
public class SingletonExample1 {

    private static final Integer a=0;
    //私有构造函数
    private SingletonExample1(){}

    //单例对象
    private static SingletonExample1 instance=null;

    //静态的工厂方法//线程安全，性能开销
    public static synchronized SingletonExample1 getInstance(){
        if(instance==null){
            //多线程可能会两个对象
            instance=new SingletonExample1();
        }
        return instance;
    }
}
