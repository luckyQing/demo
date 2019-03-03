package com.liyulin.skills.junit;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class ProductTest {

	@Test
	public void testBuy() {
		int number=1;
		Assertions.assertThat(number).isGreaterThan(0);

		List<Integer> data = Arrays.asList(1,2,3,4);
		Assertions.assertThat(data).isNotEmpty();
		
		boolean success = true;

		Assertions.assertThat(success).isTrue();
		Assertions.assertThat(success).isEqualTo(true);
	}
	
}