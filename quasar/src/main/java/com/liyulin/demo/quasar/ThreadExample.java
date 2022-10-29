package com.liyulin.demo.quasar;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        int count = 80;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("thread pool use:" + (end - start) + " ms");
    }

}