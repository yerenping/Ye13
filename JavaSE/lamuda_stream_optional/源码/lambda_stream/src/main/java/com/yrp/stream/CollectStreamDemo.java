package com.yrp.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectStreamDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");

        String[] strArray = list.stream().toArray(String[]::new);
//        System.out.println(Arrays.toString(strArray));

        List<Integer> list1 = list.stream().map(e -> e.length()).collect(Collectors.toList());

        List<String> list2 = list.stream().collect(Collectors.toCollection(() -> new ArrayList<String>()));

//        System.out.println(list1);

        System.out.println(list2);

//        String str = list.stream().collect(Collectors.joining(", ")).toString();
//        System.out.println(str);
    }
}