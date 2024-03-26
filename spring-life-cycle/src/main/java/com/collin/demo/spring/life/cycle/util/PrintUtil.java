package com.collin.demo.spring.life.cycle.util;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PrintUtil {

    private static final Set<String> METHODS = Collections.synchronizedSet(new LinkedHashSet<>());

    public static void add(String s) {
//        if (!METHODS.contains(s)) {
            METHODS.add(s);
//        }
    }

    public static void print(){
        System.out.println("\n\n\n");
        METHODS.forEach(System.out::println);
    }

}