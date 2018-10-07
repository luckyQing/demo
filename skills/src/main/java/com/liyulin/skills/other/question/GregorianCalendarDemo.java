package com.liyulin.skills.other.question;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 利用GregorianCalendar()打印日历
 *
 * @author liyulin
 * @date 2018年10月7日下午11:54:07
 */
public class GregorianCalendarDemo {

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH); // 设置本地区时间格式
		GregorianCalendar test = new GregorianCalendar();// 定义一个标准日历
		int today = test.get(Calendar.DAY_OF_MONTH); // 获得日期
		int month = test.get(Calendar.MONTH); // 获得月份（月份[0,11]分别代表1至12月）

		test.set(Calendar.DAY_OF_MONTH, 1); // 将test设置为这个月的第一天
		int weekday = test.get(Calendar.DAY_OF_WEEK); // 得到今天是星期几

		int firstDayOfWeek = test.getFirstDayOfWeek(); // 获得当前用户所在地区，一个星期中的第一天是星期几（星期天还是星期一）
		/********************************* 决定第一行需要的空格 ********************************/
		int indent = 0;
		while (weekday != firstDayOfWeek) {
			indent++;
			test.add(Calendar.DAY_OF_MONTH, -1);
			weekday = test.get(Calendar.DAY_OF_WEEK);
		}
		/********************************* 打印星期的名字 **************************************/
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();// 定义一个星期名字字符串数组

		/********************************* 打印星期的名字 **************************************/
		do {
			System.out.printf("%4s", weekdayNames[weekday]);
			test.add(Calendar.DAY_OF_MONTH, 1);
			weekday = test.get(Calendar.DAY_OF_WEEK);
		} while (weekday != firstDayOfWeek);

		/********************************* 打印第一行的空格 *************************************/
		System.out.println();
		for (int i = 0; i < indent; i++) {
			System.out.print("    ");
		}

		test.set(Calendar.DAY_OF_MONTH, 1);
		do {
			int day = test.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);

			if (day == today) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}

			test.add(Calendar.DAY_OF_MONTH, 1);
			weekday = test.get(Calendar.DAY_OF_WEEK);

			if (weekday == firstDayOfWeek) {
				System.out.println();
			}
		} while (test.get(Calendar.MONTH) == month);

		if (weekday != firstDayOfWeek) {
			System.out.println();
		}
	}

}
