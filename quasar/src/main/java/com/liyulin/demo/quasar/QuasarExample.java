package com.liyulin.demo.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.concurrent.CountDownLatch;

public class QuasarExample {

    public static void main(String[] args) throws InterruptedException {
        int count = 80;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            new Fiber<>((SuspendableRunnable) () -> {
                Fiber.sleep(1000);
                countDownLatch.countDown();
                System.out.println("Thread["+Thread.currentThread().getName()+"]:" + countDownLatch.getCount());
            }).start();
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("Fiber use:" + (end - start) + " ms");
    }

}