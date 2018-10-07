package com.liyulin.skills.string.chinese;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 生成随机汉字 原理：所有的国标汉字及符号分配在一个94行、94列的方阵中，方阵的每一行称为一个“区”， 编号为01区到94区。
 * 区位码的前两位是它的区号，后两位是它的位号。汉字的机内码是在 汉字的区位码的区码和位码上分别加上A0H（此处H表示十六进制）而得到。
 * 
 * @author liyulin
 * @version 1.0 15/07/2013
 */
public class RandomChineseUtil {

	/**
	 * 随机生成汉字的机内码
	 * 
	 * @return zbCode[] 汉字的机内码，每个元素存储一位
	 */
	public static String[] getZBCode() {
		String[] zbCode = new String[4];// 存储4位区位码
		Random random = new Random();
		String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		// 生成第1位的区位码
		int r1 = random.nextInt(3) + 11;// 生成11~14之间的随机数
		zbCode[0] = rBase[r1];

		// 生成第2位的区码
		int r2;
		if (r1 == 13) {
			r2 = random.nextInt(7);// 生成0~7之间的随机数
		} else {
			r2 = random.nextInt(16);// 生成0~16之间的随机数
		}
		zbCode[1] = rBase[r2];

		// 生成第1位的位码
		int r3 = random.nextInt(6) + 10;// 生成10~16之间的随机数
		zbCode[2] = rBase[r3];

		// 生成第2位的位码
		int r4;
		if (r3 == 10) {
			r4 = random.nextInt(15) + 1;// 生成1~16之间的随机数
		} else if (r3 == 15) {
			r4 = random.nextInt(15);// 生成1~15之间的随机数
		} else {
			r4 = random.nextInt(16);// 生成1~16之间的随机数
		}
		zbCode[3] = rBase[r4];
		return zbCode;
	}

	/**
	 * 将汉字机内码转换为汉字
	 * 
	 * @return 一个随机汉字
	 * @throws UnsupportedEncodingException
	 */
	public static String getChinese() throws UnsupportedEncodingException {
		byte[] bytes = new byte[2];
		int tempLow = Integer.parseInt(getZBCode()[0] + getZBCode()[1], 16);
		// 将生成的区码保存到字节数组的第1个元素中
		bytes[0] = (byte) tempLow;

		int tempHigh = Integer.parseInt(getZBCode()[2] + getZBCode()[3], 16);
		// 将生成的区码保存到字节数组的第2个元素中
		bytes[1] = (byte) tempHigh;
		return new String(bytes, "GBK");// 根据字节数组生成汉字
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		for (int i = 0; i < 10; i++) {
			System.out.println("汉字：" + getChinese());
		}
	}
}
