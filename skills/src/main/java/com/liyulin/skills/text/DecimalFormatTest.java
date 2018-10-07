package com.liyulin.skills.text;

import java.text.DecimalFormat;

import lombok.extern.slf4j.Slf4j;

/**
 * 设置float的精度
 */
@Slf4j
public class DecimalFormatTest {

	public static void main(String[] args) {
		float a = 1 / 3.0f;
		DecimalFormat df = new DecimalFormat("0.0000 ");

		log.info(df.format(a));
	}

}
