package com.liyulin.skills.util.concurrent.cas;

import java.util.concurrent.TimeUnit;

public class CASTest {

	public static void main(String[] args) {
		final CASCounter casCounter = new CASCounter();
		for (int i = 0; i < 9; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					casCounter.increment();
				}
			}).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(casCounter.getCounter());
	}

}
