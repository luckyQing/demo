package com.liyulin.junit5.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Junit5Example1Test {

	@Test
	public void test() {
	}

	@Nested
	class Condition1 {
		@Test
		public void test() {

		}
	}
	
	@Nested
	@Disabled
	class Condition2 {
		@Test
		public void test() {

		}
	}

	@RepeatedTest(value = 5)
	public void testRepeat() {
	}

}