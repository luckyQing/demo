package com.liyulin.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 希尔排序
 * 
 * <p>
 * 1、思想：将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中
 * 记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）
 * 对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后， 排序完成。<br>
 * 2、时间复杂度：O(nlogn)
 *
 * @author liyulin
 * @version 1.0 2013-10-20 下午4:00:14
 */
@Slf4j
public class ShellSort {

	/**
	 * 希尔排序
	 * 
	 * @param arr 待排序的数
	 * @return int[]
	 */
	public static int[] shellSort(int[] arr) {
		double d = arr.length;
		while (true) {
			d = Math.ceil(d / 2);
			int dd = (int) d;
			for (int i = 0; i < dd; i++) {// 遍历每组数据
				for (int j = i + dd; j < arr.length; j += dd) {// 对每组数据进行直接插入排序
					int temp = arr[j];
					int k = j - dd;
					for (; k >= 0; k -= dd) {
						if (temp < arr[k]) {
							arr[k + dd] = arr[k];
						} else {
							break;
						}
					}
					arr[k + dd] = temp;
				}
			}
			if (dd == 1) {// 区间大小为1时停止
				break;
			}
		}

		return arr;
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 1, 4, 5, 7, 8, 2, 6, 10 };
		data = shellSort(data);
		for (int i = 0; i < data.length; i++) {
			log.info("{}", data[i]);
		}
	}

}
