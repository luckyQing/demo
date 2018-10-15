package com.liyulin.skills.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * jdk8新特性“lambda”表达式
 *
 * @author luckytom
 * @version 1.0 2018年3月23日 下午3:48:09
 */
@Slf4j
public class LambdaTest {

	/**
	 * lambda表达式
	 */
	@Test
	public void lambdaTest() {
		// 匿名对象
		Runnable runnable = () -> {
			log.info("run");
		};
		new Thread(runnable).start();

		List<Integer> data = Arrays.asList(1, 2, 5, 8, 23, 3, 6, 8, 3, 6, 7, 9, 3, 1);
		Collections.sort(data, (a, b) -> {
			return a - b;
		});

		data.forEach(System.out::println);

		log.info("======stream=========");

		data.stream().filter(a -> {
			return a > 10;
		}).forEach(System.out::println);

		boolean tag = data.parallelStream().allMatch(a -> {
			return a > 2;
		});
		log.info("tag={}", tag);

		long c = data.parallelStream().filter(a -> {
			return a > 2;
		}).count();
		log.info("c={}", c);

		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.forEach((k, v) -> {
			log.info("k={}; v={}", k, v);
		});
	}

}
