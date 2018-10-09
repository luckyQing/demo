package com.liyulin.skills.thread.implement;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				for (int i = 0; i < 5; i++) {
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println(i);
				}
				return 5;
			}
		});
		futureTask.run();
		System.out.println("result=" + futureTask.get());
	}

}