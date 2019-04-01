package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式，===>双重同步锁单例模式，非线程安全
 * 单线程使用，多线程出现指令重排
 */
@ThreadSafe
public class SingletonExample4 {
    //私有构造函数
    private SingletonExample4(){}

    //1.memory =allocate()分配对象的内存空间
    //2.ctorInstance()初始化对象
    //3.instance=memory设置instance指向刚分配的内存空间


    //JVM和Cpu优化发生指令重排序  使用
    //1.memory =allocate()分配对象的内存空间
    //3.instance=memory设置instance指向刚分配的内存空间
    //2.ctorInstance()初始化对象



    //单例对象  volatile+双重检测机制= =》禁止指令重排序
    private volatile static SingletonExample4 instance=null;

    //静态的工厂方法//线程安全，性能开销
    public static SingletonExample4 vgetInstance(){

        if(instance==null){   //双重检测机制   //B
            //多线程可能会两个对象
            synchronized(SingletonExample4.class){  //同步锁
                if(instance==null) {
                    instance = new SingletonExample4(); //A-3
                }
            }
        }
        return instance;
    }
}
