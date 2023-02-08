package com.yrp.guava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;

/**
 * @author: YeRenping
 * @Date: 2023/1/13
 * @Description:
 */
public class GuavaDemo {
    public static void main(String[] args) {
        // ImmutableMap的of方法
        Map<String,Integer> items = ImmutableMap.of("coin", 3, "glass", 4, "pencil", 1);
        Set<Map.Entry<String, Integer>> entries = items.entrySet();
        entries.stream()
                .forEach(entry -> System.out.println(entry.getKey() +">" + entry.getValue()));

        // ImmutableList.
        List<Integer> l1 = ImmutableList.of(1, 2);
        ArrayList<Integer> l2 = Lists.newArrayList(3, 4);
        l2.stream()
                .forEach(e -> System.out.println(e));

        //ImmutableSortedSet
        Set<String> of = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println(of);
    }
}
