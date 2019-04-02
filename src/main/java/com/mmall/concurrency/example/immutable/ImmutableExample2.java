package com.mmall.concurrency.example.immutable;


import com.google.common.collect.Maps;
import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private final static Integer a=1;
    private final static String b="3";
    private static Map<Integer,Integer> map=Maps.newHashMap();

     static {
         map.put(1,2);
         map.put(3,4);
         map.put(5,6);
         map=Collections.unmodifiableMap(map);
     }

    public static void main(String[] args) {
//        a=2;
//        b="3";
//        map=Maps.newHashMap();
        map.put(1,3);
        log.info("{}",map.get(1));

    }

    private void test(final int a){
         //不允许修改
         //a=3;

    }
}
