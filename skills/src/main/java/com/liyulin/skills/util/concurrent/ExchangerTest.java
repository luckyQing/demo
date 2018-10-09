package com.liyulin.skills.util.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 线程间交换数据
 *
 * @author luckytom
 * @version 1.0 2017年12月19日 下午11:27:15
 */
public class ExchangerTest {

	public static void main(String[] args) {
		final Exchanger<String> exchanger = new Exchanger<String>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String result = exchanger.exchange("A", 10, TimeUnit.SECONDS);
					System.out.println("A="+result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String result = exchanger.exchange("B", 10, TimeUnit.SECONDS);
					System.out.println("B="+result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
