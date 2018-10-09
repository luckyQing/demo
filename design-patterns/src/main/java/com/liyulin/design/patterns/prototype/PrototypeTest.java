package com.liyulin.design.patterns.prototype;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 原型模式测试类
 *
 * @author liyulin
 * @version 1.0 2014-10-11 上午11:04:51
 */
@Slf4j
public class PrototypeTest {

	public static void main(String[] args) {
		PrototypeDemo prototypeDemo1 = new PrototypeDemo();

		List<String> contents = new ArrayList<String>();
		contents.add("111111");
		contents.add("222222");
		contents.add("333333");
		prototypeDemo1.setContents(contents);

		PrototypeDemo prototypeDemo2 = prototypeDemo1.clone();

		log.info("{}", prototypeDemo1.getContents());
		log.info("{}", prototypeDemo2.getContents());

		contents.add("444444");
		prototypeDemo1.setContents(contents);
		log.info("=========================");
		log.info("{}", prototypeDemo1.getContents());
		log.info("{}", prototypeDemo2.getContents());
	}

}
