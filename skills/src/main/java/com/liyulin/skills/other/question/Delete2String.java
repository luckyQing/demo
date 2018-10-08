package com.liyulin.skills.other.question;

import lombok.extern.slf4j.Slf4j;

/**
 * 把连续重复的字符只保留一个
 *
 * @author liyulin
 * @version 1.0 2013-10-16 下午11:32:04
 */
@Slf4j
public class Delete2String {

	/**
	 * 把连续重复的字符只保留一个
	 * 
	 * @param str 指定的字符串
	 * @return String
	 */
	public static String deleteExtraString(String str) {
		for (int i = 0; i < str.length(); i++) {
			str = str.replaceAll("[" + str.charAt(i) + "]{2,}", String.valueOf(str.charAt(i)));
		}
		return str;
	}

	public static void main(String[] args) {
		log.info(Delete2String.deleteExtraString("aaaaaassssseeeeeaefadsassssss"));
	}

}
