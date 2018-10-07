package com.liyulin.skills.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 遍历map
 *
 * @author liyulin
 * @date 2018年10月7日下午11:17:43
 */
@Slf4j
public class HashMapDemo {

	private Map<String, String> buildMapData() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "零");
		map.put("1", "壹");
		map.put("2", "贰");
		map.put("3", "叁");
		map.put("4", "肆");
		map.put("5", "伍");
		map.put("6", "陆");
		return map;
	}

	/**
	 * 遍历Map的方式
	 */
	@Test
	public void printMap() {
		Map<String, String> map = buildMapData();
		// 第一种遍历方式
		for (int i = 0, size = map.size(); i < size; i++) {
			log.info(map.get(String.valueOf(i)));
		}

		// 第二种遍历方式
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();// 键
			String values = entry.getValue();// 值
			log.info(key + "\t" + values);
		}
	}

}
