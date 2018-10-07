package com.liyulin.skills.other.question;

import lombok.extern.slf4j.Slf4j;

/**
 * 获得字符串的实际长度
 * 
 * @author liyulin
 * @version 1.0 08/07/2013
 */
@Slf4j
public class StringLenTest {

	public static int getStringLen(String s) {
		int len = 0;
		if (s == null || s.length() == 0) {
			len = 0;
		} else {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
					len++;
				} else if (Character.isLetter(c)) {
					len = len + 2;
				} else {
					len++;
				}
			}
		}
		return len;
	}

	public static void main(String[] args) {
		log.info("{}", getStringLen("中国adf?"));
	}
}
