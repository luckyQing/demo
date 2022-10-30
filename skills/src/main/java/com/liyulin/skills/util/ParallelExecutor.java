package com.liyulin.skills.util;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发处理器
 *
 * @author collin
 * @date 2022-10-30
 */
public class ParallelExecutor {

    /**
     * 并行处理线程池
     */
    private ThreadPoolExecutor threadPoolTaskExecutor = null;

    /**
     * 构造并行处理器
     *
     * @param corePoolSize     核心线程数
     * @param maximumPoolSize  最大线程池
     * @param keepAliveSeconds 非核心线程存活时间（单位：秒）
     */
    public ParallelExecutor(int corePoolSize, int maximumPoolSize, long keepAliveSeconds) {
        this.threadPoolTaskExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveSeconds, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 提交任务到线程池，异步处理
     *
     * @param data
     * @param counter
     * @param parallelable
     * @param <T>
     */
    public <T> void runAsync(List<T> data, AtomicInteger counter, Parallelable<T> parallelable) {
        if (data == null || data.isEmpty()) {
            return;
        }

        counter.getAndAdd(data.size());
        data.forEach(item -> {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    parallelable.run(item);
                } finally {
                    if (counter.decrementAndGet() == 0) {
                        synchronized (counter) {
                            counter.notify();
                        }
                    }
                }
            });
        });
    }

    /**
     * 等待线程池处理完本次任务
     *
     * @param counter
     * @throws InterruptedException
     */
    public static void await(AtomicInteger counter) {
        if (counter.get() <= 0) {
            return;
        }

        synchronized (counter) {
            if (counter.get() > 0) {
                try {
                    counter.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FunctionalInterface
    public interface Parallelable<D> {

        /**
         * 执行任务逻辑
         *
         * @param data
         */
        void run(D data);

    }

}