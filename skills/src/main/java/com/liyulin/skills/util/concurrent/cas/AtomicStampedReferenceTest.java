package com.liyulin.skills.util.concurrent.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(0, 0);

	public static void main(String[] args) throws InterruptedException {
		final int stamp = atomicStampedReference.getStamp();
		Integer reference = atomicStampedReference.getReference();
		System.out.println(reference + "============" + stamp);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Integer reference = atomicStampedReference.getReference();
				boolean tag = atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1);
				System.out.println(reference + "-" + stamp + "-"
						+ tag);
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Integer reference = atomicStampedReference.getReference();
				boolean tag = atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1);
				System.out.println(reference + "-" + stamp + "-" + tag);
			}
		});
		t1.start();
		t1.join();
		t2.start();
		t2.join();

		System.out.println(atomicStampedReference.getReference());
		System.out.println(atomicStampedReference.getStamp());
	}
}
