package com.liyulin.skills.grammar.array;

/**
 * 存储相同数据，一维数组、二维数组所占内存情况：<br>
 * 1、大小不大于2的19次方时，一维与二维数组相同；超过时，大小不同<br>
 * 2、一维数组的存储大小是有限制的，并不是无限的。
 * 
 * @author liyulin
 * @version 1.0 07/07/2013
 */
public class ArrayMemoryTest {

	// 数组的大小
	private static final int SIZE = 2 << (20 - 1);// 2的20字方

	// 一维数组
	public static void oneArrayMemory() {
		int[] arr = new int[SIZE << 1];// 数组大小乘2
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}

		// 获得占用内存总数，并将单位转换为M
		long memory = Runtime.getRuntime().totalMemory() / (2 << (20 - 1));
		System.out.println("一维数组所占内存总量为：" + memory);
	}

	// 二维数组
	public static void twoArrayMemory() {
		int[][] arr = new int[SIZE][2];
		for (int i = 0; i < arr.length; i++) {
			arr[i][0] = i;
			arr[i][1] = i;
		}

		// 获得占用内存总数，并将单位转换为M
		long memory = Runtime.getRuntime().totalMemory() / (2 << (20 - 1));
		System.out.println("二维数组所占内存总量为：" + memory);
	}

	public static void main(String[] args) {
		System.out.println(SIZE);
		oneArrayMemory();
		twoArrayMemory();
	}

}
