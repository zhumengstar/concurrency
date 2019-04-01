package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;

//推荐
@ThreadSafe
@Recommend
public class SingletonExample6 {
    //私有构造函数
    private SingletonExample6(){}

    //静态的工厂方法
    public static SingletonExample6 getInstance(){

        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton{
        INSTANCE;
        private SingletonExample6 singleton;

        //JVM保证调用一次，
        Singleton(){
            singleton=new SingletonExample6();
        }
        public SingletonExample6 getInstance(){
            return singleton;
        }
    }
}
