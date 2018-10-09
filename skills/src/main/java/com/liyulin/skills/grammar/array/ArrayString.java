package com.liyulin.skills.grammar.array;

import java.util.Arrays;

/**
 * 1、Arrays.toString只能用于一维数组，如果用于二维以上数组，则只会打印数组元素的地址<br>
 * 2、Array.deepToString不能用于一维数组；否则报错。<br>
 * 3、Array.deepToString方法会将内嵌数组的内容也打印出来,不管数组内嵌多少层,都会循环递归到最内层的数据
 */
public class ArrayString {

	public static void main(String[] args) {
		/******************* 一维数组 ******************/
		int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(Arrays.toString(arr1));
		/******************* 二维数组 ******************/
		int[][] arr2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(Arrays.deepToString(arr2));
		/******************* 三维数组 ******************/
		int[][][] arr3 = { { { 111, 222, 333 }, { 444, 555, 666 }, { 777, 888, 999 } },
				{ { 111, 222, 333 }, { 444, 555, 666 }, { 777, 888, 999 } },
				{ { 111, 222, 333 }, { 444, 555, 666 }, { 777, 888, 999 } } };
		System.out.println(Arrays.deepToString(arr3));
	}

}
