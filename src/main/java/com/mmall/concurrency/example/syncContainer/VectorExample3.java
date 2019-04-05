package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

@Slf4j
@NotThreadSafe//Vector线程可能不安全
public class VectorExample3 {

    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1) {
        for (Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }

        }
    }
    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> iterator=v1.iterator();
        while (iterator.hasNext()){
            Integer i=iterator.next();
            if(i==3){
                v1.remove(i);
            }
        }
    }

    //success
    private static void test3(Vector<Integer> v1) {
        for (int i = 0; i < v1.size(); i++) {
            if(v1.get(i)==3){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> v=new Vector<>();
        v.add(1);
        v.add(2);
        v.add(3);
//        test1(v);//fail
//        test2(v);//fail
        test3(v);//success
    }
}
