package com.liyulin.skills.other.question;

import lombok.extern.slf4j.Slf4j;

/**
 * 回文判断
 * 
 * @author liyulin
 * @version 1.0 2013-10-16 下午4:16:39
 */
@Slf4j
public class HuiWen {

	/**
	 * 判断指定字符串是否是回文
	 * 
	 * @param str
	 * @return 回文标识：{@code true}，回文；{@code false}，不是回文
	 */
	public static boolean isHuiWen(String str) {
		for (int i = 0, size = str.length(); i < size / 2; i++) {
			if (str.charAt(i) != str.charAt(size - i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		log.info("{}", HuiWen.isHuiWen("ass123321dssa"));
	}
}
