package com.mmall.concurrency.example.immutable;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    //
//    private final static List<Integer> list=ImmutableList.of(1,2,3,4,5);
    private final static ImmutableList list=ImmutableList.of(1,2,3,4,5);

    private final static ImmutableSet set=ImmutableSet.copyOf(list);

    private final static ImmutableMap map1=ImmutableMap.of(1,2,3,4);

    private final static ImmutableMap<Integer,Integer> map2=ImmutableMap
            .<Integer,Integer>builder()
            .put(1,2).put(2,3).put(4,5)
            .build();

    public static void main(String[] args) {
        //不允许修改
        list.add(2);
        set.add(2);
        map1.put(5,3);
        System.out.println(map2.get(2));
    }

}
