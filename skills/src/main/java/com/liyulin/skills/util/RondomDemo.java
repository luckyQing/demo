package com.liyulin.skills.util;

import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 生成随机数
 *
 * @author liyulin
 * @date 2018年10月7日下午11:49:02
 */
@Slf4j
public class RondomDemo {

	@Test
	public void test1() {
		// 产生[0,9]的随机数
		for (int i = 0; i < 10; i++) {
			int num = (int) (Math.random() * 10);
			log.info("<<<<" + num);
		}
	}

	@Test
	public void test2() {
		// 产生[0,9]的随机数
		for (int i = 0; i < 10; i++) {
			int num = (new Random()).nextInt(10);
			log.info("<<<<" + num);
		}
	}

}
