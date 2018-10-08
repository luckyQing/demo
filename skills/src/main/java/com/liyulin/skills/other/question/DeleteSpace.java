package com.liyulin.skills.other.question;

import lombok.extern.slf4j.Slf4j;

/**
 * 删除一个字符串中连续出现超过一次的空格
 * 
 * @author liyulin
 * @version 2013-10-16 下午4:43:52
 */
@Slf4j
public class DeleteSpace {

	/**
	 * 删除一个字符串中连续出现超过一次的空格
	 * 
	 * @param str 指定字符串
	 * @return String
	 */
	public static String delete(String str) {
		str = str.replaceAll("[ ]{2,}", " ");
		return str;
	}

	public static void main(String[] args) {
		log.info(DeleteSpace.delete("    ss    saa   s   ss"));
	}

}
