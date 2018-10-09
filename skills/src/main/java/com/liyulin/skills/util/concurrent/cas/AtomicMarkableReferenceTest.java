package com.liyulin.skills.util.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceTest {
	private AtomicMarkableReference<Long> atomicMarkableReference = new AtomicMarkableReference<Long>(0L, false);

	public void increment() {
		while (!atomicMarkableReference.compareAndSet(atomicMarkableReference.getReference(),
				atomicMarkableReference.getReference() + 1, atomicMarkableReference.isMarked(),
				!atomicMarkableReference.isMarked()));
	}

	public long getCounter() {
		return atomicMarkableReference.getReference();
	}

	public static void main(String[] args) throws InterruptedException {
		final AtomicMarkableReferenceTest atomicMarkableReferenceTest = new AtomicMarkableReferenceTest();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					atomicMarkableReferenceTest.increment();
				}
			}).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(atomicMarkableReferenceTest.getCounter());
	}
}
