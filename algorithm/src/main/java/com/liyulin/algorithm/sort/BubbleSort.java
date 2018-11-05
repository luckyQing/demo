package com.liyulin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡排序
 * 
 * <p>
 * 1、思想：将序列划分为无序和有序区，不断通过交换较大元素至无序区尾完成排序。<br>
 * 2、时间复杂度：O(n^2)
 *
 * @author liyulin
 * @version 1.0 2013-10-17 上午11:35:16
 */
@Slf4j
public class BubbleSort {

	/**
	 * 冒泡排序
	 * 
	 * @param arr 待排序的数
	 * @return int[]
	 */
	public static int[] bubbleSort(int[] arr) {
		int size = arr.length;
		for (int i = 0; i < size - 1; i++) {
			boolean isSorted = true;
			for (int j = 0; j < size - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					isSorted = false;
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			// 如果没有发生交换，说明数组已经排序好了
			if (isSorted) {
				break;
			}
		}

		return arr;
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 1, 4, 5, 7, 8, 2, 6, 2, 6, 7, 3, 4 };
		data = BubbleSort.bubbleSort(data);
		for (int i = 0; i < data.length; i++) {
			log.info("{}", data[i]);
		}
	}

}
