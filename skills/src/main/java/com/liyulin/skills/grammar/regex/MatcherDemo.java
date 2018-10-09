package com.liyulin.skills.grammar.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 正则表达式匹配
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@Slf4j
public class MatcherDemo {

	public static void main(String[] args) {
		String regex = "\\bTom\\b";
		String input = "Tom Tom Tom Tome";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		int index = 0;
		while (matcher.find()) {
			// System.out.println(matcher.matches());
			index++;
			log.info("{} match; start at:{}; end at:{}", index, matcher.start(), matcher.end());
			log.info("第{}个：", index);
			for (int i = matcher.start(); i < matcher.end(); i++) {
				log.info("{}", input.charAt(i));
			}
			System.out.println();
		}
	}

}
