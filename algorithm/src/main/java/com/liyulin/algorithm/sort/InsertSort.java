package com.liyulin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 直接插入排序
 * 
 * <p>
 * 1、基本思想：在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排好顺序的，
 * 现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。如此反复循环， 直到全部排好顺序。<br>
 * 2、性能：O(n2)
 *
 * @author liyulin
 * @version 1.0 2013-10-20 上午11:43:07
 */
@Slf4j
public class InsertSort {

	/**
	 * 直接插入排序
	 * 
	 * @param arr 待排序的数
	 * @return int[]
	 */
	public static int[] insertSort(int[] arr) {
		int temp = 0;
		for (int i = 1, size = arr.length; i < size; i++) {
			temp = arr[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (temp < arr[j]) {// 将大于temp的值整体后移一个单位
					arr[j + 1] = arr[j];
				}
			}
			arr[j + 1] = temp;
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 4, 15, 6, 7, 8, 2, 7, 3, 7, 6 };
		insertSort(arr);

		for (int i = 0, size = arr.length; i < size; i++) {
			log.info("{}", arr[i]);
		}
	}

}
