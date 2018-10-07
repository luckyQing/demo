package com.liyulin.skills.text;

import java.text.NumberFormat;

import lombok.extern.slf4j.Slf4j;

/**
 * 格式化数值
 * 
 * @author liyulin
 * @version 1.0 08/07/2013
 */
@Slf4j
public class NumberFormatTest {

	public static void main(String[] args) {
		NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();// 返回货币格式
		String money = moneyFormat.format(123.34532342);
		log.info("格式化为货币的效果：{}", money);

		NumberFormat integerFormat = NumberFormat.getIntegerInstance(); // 返回整数格式
		String integer = integerFormat.format(123.34532342);
		log.info("格式化为整数的效果：{}", integer);

		NumberFormat percentFormat = NumberFormat.getPercentInstance(); // 返回百分数格式
		String percent = percentFormat.format(0.123);
		log.info("格式化为百分数的效果：{}", percent);
	}

}
