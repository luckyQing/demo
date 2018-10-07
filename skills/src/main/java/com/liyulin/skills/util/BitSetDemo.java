package com.liyulin.skills.util;

import java.util.BitSet;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BitSetDemo {

	@Test
	public void test(){
		BitSet bitSet = new BitSet(Integer.MAX_VALUE);
		  
		// 0x7FFFFFFF
		String url = "http://baidu.com/a";
		int hashcode = url.hashCode() & 0x7FFFFFFF;
		bitSet.set(hashcode);
		
		// 着色位的个数
		log.info("着色位的个数：{}", bitSet.cardinality());
		// 检测存在性 
		log.info("检测存在性：{}", bitSet.get(hashcode));
		// 清除位数据
		bitSet.clear(hashcode);
		log.info("清除位数据：{}", ((4-1)>>6)+1);
		
	}

}