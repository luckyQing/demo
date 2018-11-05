package com.liyulin.algorithm.search;

import lombok.extern.slf4j.Slf4j;

/**
 * 顺序查找
 * 
 * <p>
 * 1、思想：从头到尾，一个一个地比，找着相同的就成功，找不到就失败。<br>
 * 2、平均查找长度=（n+1）/2
 * 
 * @author liyulin
 * @version 1.0 2013-10-20 上午11:12:25
 */
@Slf4j
public class SequentialSearch {

	/**
	 * 顺序查找
	 * 
	 * @param arr 所有数据
	 * @param data 要查找的数
	 * @return int
	 */
	public static int sequentialSearch(int[] arr, int data) {
		for (int i = 0, size = arr.length; i < size; i++) {
			if (arr[i] == data) {// 找到
				return i;
			}
		}
		return -1;// 未找到
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		log.info("index={}", sequentialSearch(array, 88));
	}
	
}
