package com.liyulin.redis;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class RedissonTest {

    @Autowired
    private Redisson redisson;
    int sum = 0;

    /**
     * 可重入锁
     *
     * @throws InterruptedException
     */
    @Test
    public void testRLock() throws InterruptedException {
        RLock lock = redisson.getLock("anyLock");
        int count = 5;
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    sum++;
                    log.info("sum={}", sum);
                } finally {
                    lock.unlock();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(5);
        Assertions.assertThat(sum).isEqualTo(count);
    }

    /**
     * 联锁
     */
    @Test
    public void testRedissonMultiLock() {
        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");

        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
        // 同时加锁：lock1 lock2 lock3
        // 所有的锁都上锁成功才算成功。
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testTryLock1() throws InterruptedException {
        RLock lock = redisson.getLock("testTryLock1");
        boolean lockState = false;
        try {
            // 通过“看门狗”不断刷新线程有效期，避免锁的时间小于业务执行时间
            lockState = lock.tryLock();
            TimeUnit.SECONDS.sleep(60);
        } finally {
            if (lockState) {
                lock.unlock();
            }
        }
    }

    @Test
    public void testTryLock2() throws InterruptedException {
        RLock lock = redisson.getLock("testTryLock2");
        boolean lockState = false;
        try {
            // 通过“看门狗”不断刷新线程有效期，避免锁的时间小于业务执行时间
            log.info("lock tryLock");
            lockState = lock.tryLock();
            log.info("lock success");
            TimeUnit.SECONDS.sleep(10);
        } finally {
            if (lockState) {
                log.info("lock again start");
                lock.lock(10, TimeUnit.SECONDS);
                log.info("lock again end");
                lock.unlock();
            }
        }
    }

}
