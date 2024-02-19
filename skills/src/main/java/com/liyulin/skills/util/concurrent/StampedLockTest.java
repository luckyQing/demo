package com.liyulin.skills.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    static int value = 0;

    public static void main(String[] args) throws InterruptedException {

        int count = 100;
        StampedLock stampedLock = new StampedLock();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                }
                long readLockStamp = 0;
                try {
                    readLockStamp = stampedLock.readLock();
                    System.out.println(Thread.currentThread() + "-->" + StampedLockTest.value);
                } finally {
                    stampedLock.unlockRead(readLockStamp);
                }
            }, String.format("thread-read-%d", i)).start();
        }

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(499);
                } catch (InterruptedException e) {
                }
                long readLockStamp = 0;
                try {
                    readLockStamp = stampedLock.writeLock();
                    StampedLockTest.value++;
                    System.out.println(Thread.currentThread() + "-->" + StampedLockTest.value);
                } finally {
                    stampedLock.unlockWrite(readLockStamp);
                }
            }, String.format("thread-write-%d", i)).start();
        }


        TimeUnit.MILLISECONDS.sleep(1200);
    }

}