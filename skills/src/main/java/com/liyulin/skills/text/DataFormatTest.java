package com.liyulin.skills.text;

import java.text.DateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * 格式化日期
 * 
 * @author liyulin
 * @version 1.0 08/07/2013
 */
@Slf4j
public class DataFormatTest {

	public static void main(String[] args) {
		DateFormat nFormat = DateFormat.getDateInstance(DateFormat.LONG); // 返回长日期格式
		String value = nFormat.format(new Date()); // 格式化为长日期
		log.info("格式化为长日期的效果：{}", value);

		nFormat = DateFormat.getDateInstance(); // 返回标准日期格式
		value = nFormat.format(new Date());// 格式化为长日期
		log.info("格式化为标准日期的效果：{}", value);

		nFormat = DateFormat.getDateInstance(DateFormat.SHORT);// 返回短日期格式
		value = nFormat.format(new Date());// 格式化为长日期
		log.info("格式化为短日期的效果：{}", value);

		nFormat = DateFormat.getTimeInstance(DateFormat.SHORT);// 返回带上下午标记的时间格式
		value = nFormat.format(new Date());// 格式化为带上下午标记的时间
		log.info("带上下午标记的时间效果：{}", value);

		nFormat = DateFormat.getTimeInstance();// 返回标准时间格式
		value = nFormat.format(new Date());// 格式化为标准时间
		log.info("格式化为标准的时间效果：{}", value);
	}

}
