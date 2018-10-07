package com.liyulin.skills.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * 计算程序运行时间
 *
 * @author liyulin
 * @date 2018年10月7日下午11:34:29
 */
@Slf4j
public class TimeOfProgram {

	public static void main(String[] args) {
		int sum = 0;
		long startTime = System.nanoTime(); // 获取计算前时系统时间
		for (int i = 0; i < 10000; i++) {
			sum += i;
		}
		long endTime = System.nanoTime(); // 获取计算结束时系统时间
		long time = endTime - startTime; // 运行时间，单位：纳秒
		log.info("sum = {}", sum);
		log.info("time = {}{}", time, "ns");
	}

}
