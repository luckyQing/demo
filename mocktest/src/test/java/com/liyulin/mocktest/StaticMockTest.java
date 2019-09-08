package com.liyulin.mocktest;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;

import com.alibaba.fastjson.TypeReference;

@PrepareForTest(StringUtils.class)

//@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
//@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class StaticMockTest extends AbstractUnitTest{
	@Rule
	public PowerMockRule rule = new PowerMockRule();
	static { 
	    PowerMockAgent.initializeIfNeeded(); 
	} 

	@Test
	public void testStaticMethod() throws Exception {
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.when(StringUtils.isBlank(Mockito.any())).thenReturn(true);
		
		Boolean result = postJson("/static/isBlank", "123456", new TypeReference<Boolean>() {
		});
		Assertions.assertThat(result).isTrue();
	}


	@Test
	public void testStaticMethod1() {
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.when(StringUtils.isBlank(Mockito.any())).thenReturn(true);
		Assertions.assertThat(StringUtils.isBlank("123456")).isTrue();
	}
}