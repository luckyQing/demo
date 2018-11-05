package com.liyulin.encryption.unit.rsa;

import java.security.NoSuchAlgorithmException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.liyulin.encryption.md5.MD5Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MD5Test {

	@Test
	public void testMd5() throws NoSuchAlgorithmException {
		String text = new String("luckyQing");
		log.info("原始：{}", text);
		log.info("MD5后：{}", MD5Util.string2MD5(text));
		log.info("加密的：{}", MD5Util.convertMD5(text));

		String content = MD5Util.convertMD5(MD5Util.convertMD5(text));

		Assertions.assertThat(content).isEqualTo(text);
		log.info("解密的：{}", content);
	}

}
