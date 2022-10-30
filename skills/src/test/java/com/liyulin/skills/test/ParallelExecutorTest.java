package com.liyulin.skills.test;

import com.liyulin.skills.util.ParallelExecutor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelExecutorTest {

    @Test
    public void test() {
        ParallelExecutor parallelExecutor = new ParallelExecutor(1, 8, 60);
        AtomicInteger counter = new AtomicInteger(0);
        for (int page = 1; page <= 5; page++) {
            List<Integer> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                data.add(i);
            }

            parallelExecutor.runAsync(data, counter, (d) -> {
                System.out.println(d);
            });
        }

        parallelExecutor.await(counter);
        System.out.println("over");
    }

}