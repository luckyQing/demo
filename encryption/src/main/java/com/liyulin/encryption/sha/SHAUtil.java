package com.liyulin.encryption.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA加密
 * 
 * @author liyulin
 * @version 1.0, 10/07/2013
 */
public final class SHAUtil {

	public static String getSHA(String str) throws NoSuchAlgorithmException {
		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(str.getBytes());// 使用指定的字节更新摘要
		byte[] ss = sha.digest();// 通过执行诸如填充之类的最终操作完成哈希计算

		return bytes2String(ss);
	}

	/** 将字节数组转化为字符串 */
	private static String bytes2String(byte[] aa) {
		StringBuilder hash = new StringBuilder();
		for (int i = 0; i < aa.length; i++) {
			int temp;
			temp = aa[i] < 0 ? aa[i] + 256 : aa[i];// 如果小于0，转换成正整数
			if (temp < 16) {
				hash.append("0");
			}

			hash.append(Integer.toString(temp, 16));// 转换为16进制
		}
		return hash.toString().toUpperCase();// 转换为大写
	}

}
