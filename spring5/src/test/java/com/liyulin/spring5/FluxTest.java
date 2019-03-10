package com.liyulin.spring5;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Flux示例
 * 
 * <h2>有三种方法可以创建Flux序列</h2>
 * <ul>
 * <li>静态方法
 * <li>generate()方法
 * <li>create()方法
 * </ul>
 *
 * @author liyulin
 * @date 2019年3月10日下午4:32:36
 */
@Slf4j
public class FluxTest extends TestCase {

	/**
	 * 静态方法
	 */
	public void testStaticMethod() {
		// 可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束。
		Flux.just("Hello", "World").subscribe(log::info);
		// fromArray()，fromIterable()和 fromStream()：可以从一个数组、Iterable 对象或 Stream 对象中创建
		Flux.fromArray(new Integer[] { 1, 2, 3 }).subscribe((item) -> {
			log.info(String.valueOf(item));
		});
		
		// 创建一个不包含任何元素，只发布结束消息的序列。
		Flux.empty().subscribe((item) -> {
			log.info(String.valueOf(item));
		});
		
		// 创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
		Flux.range(10, 13).subscribe((item) -> {
			log.info(String.valueOf(item));
		});
		
		Flux.interval(Duration.of(5, ChronoUnit.SECONDS)).subscribe((item) -> {
			log.info(String.valueOf(item));
		});
		
		Flux.never().subscribe((item) -> {
			log.info(String.valueOf(item));
		});
		
		Flux.error(new NullPointerException()).subscribe((info) -> {
			log.info(String.valueOf(info));
		}, (error) -> {
			log.info(String.valueOf(error));
		});
	}

	/**
	 * generate()方法
	 */
	public void testGenerate() {
		Flux.generate(sink -> {
			sink.next("Hello");
			sink.complete();
		}).subscribe((item) -> {
			log.info(String.valueOf(item));
		});

		final Random random = new Random();
		Flux.generate(ArrayList::new, (list, sink) -> {
			int value = random.nextInt(100);
			list.add(value);
			sink.next(value);
			if (list.size() == 10) {
				sink.complete();
			}
			return list;
		}).subscribe((item) -> {
			log.info(String.valueOf(item));
		});
	}

	/**
	 * create()方法
	 */
	public void testCreate() {
		Flux.create(sink -> {
			for (int i = 0; i < 10; i++) {
				sink.next(i);
			}
			sink.complete();
		}).subscribe((item) -> {
			log.info(String.valueOf(item));
		});
	}

}