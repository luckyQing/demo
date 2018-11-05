package com.liyulin.encryption.unit.rsa;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.liyulin.encryption.rsa.RSAUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RSATest {
	static String publicKey;
	static String privateKey;

	@Before
	public void init() {
		try {
			Map<String, Object> keyMap = RSAUtils.genKeyPair();
			publicKey = RSAUtils.getPublicKey(keyMap);
			privateKey = RSAUtils.getPrivateKey(keyMap);
			log.info("公钥: {}", publicKey);
			log.info("私钥： {}", privateKey);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void testRSA() throws Exception {
		String param = "id=1&name=张三";
		byte[] encodedData = RSAUtils.encryptByPrivateKey(param.getBytes(), privateKey);
		log.info("加密后：{}", encodedData);

		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		String decodeResult = new String(decodedData);
		Assertions.assertThat(decodeResult).isEqualTo(param);
		log.info("解密后：{}", decodeResult);

		String sign = RSAUtils.sign(encodedData, privateKey);
		log.info("签名：{}", sign);

		boolean status = RSAUtils.verify(encodedData, publicKey, sign);
		Assertions.assertThat(status).isTrue();
		log.info("签名验证结果：{}", status);
	}
}
