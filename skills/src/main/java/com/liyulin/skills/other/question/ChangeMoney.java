package com.liyulin.skills.other.question;

import lombok.extern.slf4j.Slf4j;

/**
 * 将小写金额转化为大写金额
 * 
 * @author liyulin
 * @version 1.0 08/07/2013
 */
@Slf4j
public class ChangeMoney {

	public static String chang(String money) {
		String[] MyScale = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };
		String[] MyBase = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

		boolean isPoint = false;

		if (money.indexOf(".") != -1) {
			money = money.substring(0, money.indexOf(".")) + money.substring(money.indexOf(".") + 1);
			isPoint = true;
		}

		StringBuilder m = new StringBuilder();
		for (int i = money.length(); i > 0; i--) {
			int MyData = Integer.parseInt(String.valueOf(money.charAt(money.length() - i)));

			m.append(MyBase[MyData]);
			if (isPoint == true) {
				m.append(MyScale[i - 1]);
			} else {
				m.append(MyScale[i + 1]);
			}
		}
		return m.toString();
	}

	public static void main(String[] args) {
		log.info("123456.78转化为大写金额为：{}", ChangeMoney.chang("123456.78"));
	}

}
