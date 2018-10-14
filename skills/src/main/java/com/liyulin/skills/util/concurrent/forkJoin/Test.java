package com.liyulin.skills.util.concurrent.forkJoin;

import java.util.concurrent.ForkJoinPool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test extends ForkJoinPool {

	public static void main(String[] args) {
		// 初始化数据
		int size = 1000;
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = i + 1;
		}

		int parallelism = 20;
		ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);
		SumRecursiveTask sumRecursiveTask = new SumRecursiveTask(data, 0, data.length);
		int sum = forkJoinPool.invoke(sumRecursiveTask);
		log.info("fork/join sum={}", sum);
	}

}
