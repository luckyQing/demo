package com.liyulin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序
 * 
 * <p>
 * 1、基本思想：选择一个基准元素,通常选择第一个元素或者最后一个
 *    元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一
 *    部分大于等于基准元素,此时基准元素在其排好序后的正确位置,然后
 *    再用同样的方法递归地排序划分的两部分。<br>
 * 2、时间复杂度：n*log(n)。
 *
 * @author liyulin
 * @version 1.0 2013-10-20 下午2:51:33 
 */
@Slf4j
public class QuickSort {

	/**
	 * 快速排序
	 * 
	 * @param arr 要排序的数据
	 * @param low 左边下标
	 * @param high 右边下标
	 * @return int[]
	 */
	public static int[] quickSort(int[] arr, int low, int high) {
		int i = low;
		int j = high;
		if (i < j) {// 区间内至少存在2个元素的情况
			// 确定哪个指针移动
			boolean transfer = true;
			while (i != j) {// 从两端交替向中间扫描,直至i=j为止
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					// 前移还是后移；值交换一个，移动方向改变一次（初始向后移动）
					transfer = (transfer == true) ? false : true;
				}
				if (transfer) {
					j--;// 前移
				} else {
					i++;// 后移
				}
			}
			quickSort(arr, low, --i); // 对左区间递归排序
			quickSort(arr, ++j, high);// 对右区间递归排序
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 1, 4, 15, 6, 7, 8, 2, 7, 3 };
		quickSort(arr, 0, arr.length - 1);

		for (int i = 0; i < arr.length; i++) {
			log.info("{}", arr[i]);
		}
	}

}
