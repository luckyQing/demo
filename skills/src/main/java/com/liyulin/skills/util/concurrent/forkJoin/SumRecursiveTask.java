package com.liyulin.skills.util.concurrent.forkJoin;

import java.util.concurrent.RecursiveTask;

import lombok.AllArgsConstructor;

/**
 * fork/join 求和
 * 
 * <p>
 * 基本思想是将大任务分割成小任务，最后将小任务聚合起来得到结果。
 * 
 * <h3>ForkJoinTask</h3>
 * <p>
 * 我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。它提供在任务中执行fork()和join的操作机制。 fork是分解,
 * join是收集结果。<br>
 * <ul>
 * <li>RecursiveAction：用于没有返回结果的任务。<br>
 * <li>RecursiveTask：用于有返回值的任务。
 * </ul>
 * 
 * <h3>ForkJoinPool</h3>
 * <p>
 * task要通过ForkJoinPool来执行，分割的子任务也会添加到当前工作线程的双端队列中，进入队列的头部。
 * 当一个工作线程中没有任务时，会从其他工作线程的队列尾部获取一个任务。
 *
 * <h3>工作窃取（work-stealing）算法</h3>
 * <p>
 * 指某个线程从其他队列里窃取任务来执行。假如我们需要做一个比较大的任务，我们可以把这个任务分
 * 割为若干互不依赖的子任务，为了减少线程间的竞争，于是把这些子任务分别放到不同的队列里，并为每
 * 个队列创建一个单独的线程来执行队列里的任务，线程和队列一一对应，比如A线程负责处理A队列里的任务。
 * 但是有的线程会先把自己队列里的任务干完，而其他线程对应的队列里还有任务等待处理。干完活的线程与其等着，
 * 不如去帮其他线程干活，于是它就去其他线程的队列里窃取一个任务来执行。而在这时它们会访问同一个队列，
 * 所以为了减少窃取任务线程和被窃取任务线程之间的竞争，通常会使用双端队列，被窃取任务线程永远从双端
 * 队列的头部拿任务执行，而窃取任务的线程永远从双端队列的尾部拿任务执行。
 * 
 * @author liyulin
 * @date 2018年10月14日下午10:07:13
 */
@AllArgsConstructor
public class SumRecursiveTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	/** 数据 */
	private int[] data;
	private int startIndex;
	private int endIndex;
	/** 每次处理的数量 */
	private static final int THRESHOLD = 10;

	@Override
	protected Integer compute() {
		if (endIndex - startIndex > THRESHOLD) {
			int middle = (endIndex + startIndex) / 2;
			SumRecursiveTask taskHead = new SumRecursiveTask(data, startIndex, middle);
			SumRecursiveTask taskTail = new SumRecursiveTask(data, middle, endIndex);
			invokeAll(taskHead, taskTail);
			return taskHead.join() + taskTail.join();
		}

		int sum = 0;
		for (int i = startIndex; i < endIndex; i++) {
			sum += data[i];
		}
		return sum;
	}

}
