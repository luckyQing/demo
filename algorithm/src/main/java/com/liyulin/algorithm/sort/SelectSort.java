package com.liyulin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单选择排序
 * 
 * <p>
 * 1、算法步骤：<br>
 *   1）首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置<br>
 *   2）再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。<br>
 *   3）重复第二步，直到所有元素均排序完毕。<br>
 * 2、时间复杂度：O(n2)
 *
 * @author liyulin
 * @version 1.0 2013-10-20 下午3:11:06 
 */
@Slf4j
public class SelectSort {

	/**
	 * 简单选择排序
	 * 
	 * @param arr 待排序的数
	 * @return int[]
	 */
	public static int[] selectSort(int arr[]) {
		for (int i = 0, size = arr.length; i < size; i++) {
			int temp = arr[i];// 记录最小值 
			int position = i; // 记录当前位置 
			for (int j = i + 1; j < size; j++) {
				if (arr[j] < temp) {
					temp = arr[j];
					position = j;// 记录最小元素的位置
				}
			}
			// 交换最小数和第i位数的位置 
			arr[position] = arr[i];
			arr[i] = temp;
		}

		return arr;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 4, 15, 6, 7, 8, 2, 7, 3, 7, 6 };
		selectSort(arr);

		for (int i = 0, size = arr.length; i < size; i++) {
			log.info("{}", arr[i]);
		}
	}

}
