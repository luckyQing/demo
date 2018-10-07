package com.liyulin.skills.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashSetDemo {

	public Set<String> buildHashSet() {
		Set<String> set = new HashSet<String>();
		set.add("bbb");
		set.add("ddd");
		set.add("aaa");
		set.add("ccc");
		return set;
	}

	/**
	 * 遍历Set
	 */
	@Test
	public void pringSet() {
		Set<String> set = buildHashSet();
		// 第一种遍历方式
		for (String str : set) {
			log.info(str);
		}

		// 第二种遍历方式
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			log.info(iterator.next());
		}
	}
	
}
