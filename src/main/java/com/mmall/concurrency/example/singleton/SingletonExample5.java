package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式，单例实例在类装载的时候进行创建
 */
@ThreadSafe
public class SingletonExample5 {
    //私有构造函数,不能有太多处理，必须使用
    private SingletonExample5(){}
    //单例对象   顺序问题
    private static SingletonExample5 instance=null;

    static {
        instance=new SingletonExample5();
    }



    //静态的工厂方法
    public static SingletonExample5 getInstance(){

        return instance;
    }
}
