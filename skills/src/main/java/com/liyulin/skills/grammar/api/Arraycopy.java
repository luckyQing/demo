package com.liyulin.skills.grammar.api;

/**
 * 1、使用“system.arraycopy()”代替通过来循环复制数组。 
 * 2、system.arraycopy()比一般的方式复制性能要好得多。
 *
 * @author liyulin
 * @date 2013-03-02
 */
public class Arraycopy {

	public static void main(String[] args) {
		int[] arr = new int[2000];
		int[] arr1 = new int[2000];
		int[] arr2 = new int[2000];
		long startTime1, startTime2;// 计算前时系统时间
		long endTime1, endTime2; // 计算结束时系统时间
		long time1, time2;
		for (int i = 0; i < 2000; i++) {
			arr[i] = i;
		}

		startTime1 = System.nanoTime(); // 获取计算前时系统时间
		for (int i = 0; i < 2000; i++) {
			arr1[i] = arr[i];
		}
		endTime1 = System.nanoTime(); // 获取计算结束时系统时间
		time1 = endTime1 - startTime1;// 运行时间，单位：纳秒
		System.out.println("time1 = " + time1);

		startTime2 = System.nanoTime(); // 获取计算前时系统时间
		System.arraycopy(arr, 0, arr2, 0, 2000);
		endTime2 = System.nanoTime(); // 获取计算结束时系统时间
		time2 = endTime2 - startTime2;// 运行时间，单位：纳秒
		System.out.println("time2 = " + time2);
	}

}
