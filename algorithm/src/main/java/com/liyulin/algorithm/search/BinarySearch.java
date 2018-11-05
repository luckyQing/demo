package com.liyulin.algorithm.search;

import lombok.extern.slf4j.Slf4j;

/**
 * 二分查找
 * 
 * <p>
 * 1.必须采用顺序存储结构 <br>
 * 2.必须按关键字大小有序排列。 <br>
 * 3.假设其数组长度为n，其算法复杂度为o（log（n））
 *
 * @author liyulin
 * @version 1.0 2013-10-17 上午11:19:41
 */
@Slf4j
public class BinarySearch {

	/**
	 * 二分查找
	 * 
	 * @param arr 所有数据
	 * @param data 要查找的数据
	 * @return 数据所在的数组位置
	 */
	public static int binarySearch(int[] arr, int data) {
		int low = 0;
		int high = arr.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (data == arr[mid]) {// 找到了
				return mid;
			} else if (data > arr[mid]) {// 在后半部分
				low = mid + 1;
			} else if (data < arr[mid]) {// 在前半部分
				high = mid - 1;
			}
		}
		return -1;// 没有找到
	}

	public static void main(String[] args) {
		int[] array = { 1, 4, 6, 8, 8, 12, 34, 67, 78, 133, 155, 158 };
		int index = BinarySearch.binarySearch(array, 67);
		log.info("index={}", index);
	}

}
