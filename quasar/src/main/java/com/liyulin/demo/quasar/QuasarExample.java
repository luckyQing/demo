package com.liyulin.demo.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.concurrent.CountDownLatch;

public class QuasarExample {

    public static void main(String[] args) throws InterruptedException {
        int count = 80;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            new Fiber<>(new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    Fiber.sleep(1000);
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("Fiber use:" + (end - start) + " ms");
    }

}