package com.liyulin.skills.util.concurrent;

import java.util.concurrent.atomic.LongAdder;

public class LongAddrTest {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        System.out.println(longAdder.longValue());

        longAdder.increment();
        System.out.println(longAdder.sum());

        longAdder.increment();
        System.out.println(longAdder.longValue());


        longAdder.reset();

        longAdder.increment();
        System.out.println(longAdder.longValue());

    }

}