package com.liyulin.encryption.unit.rsa;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.liyulin.encryption.sha.SHAUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SHATest {
	
	@Test
	public void testSHA() throws NoSuchAlgorithmException {
		log.info(SHAUtil.getSHA("luckyQing"));
	}
	
}
