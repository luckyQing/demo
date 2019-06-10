package com.liyulin.mocktest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.mocktest.biz.AppLogBiz;
import com.liyulin.mocktest.entity.ApiLogEntity;
import com.liyulin.mocktest.mapper.ApiLogBaseMapper;
import com.liyulin.mocktest.util.MockUtil;

public class ApiLogControllerTest extends AbstractUnitTest {

	@Mock
	private ApiLogBaseMapper apiLogBaseMapper;
	@InjectMocks
	@Autowired
	private AppLogBiz aaaBiz;

	@Test
	public void testQueryAaaById() throws Exception {
		ApiLogEntity apiLogEntity = mockEntity();
		Mockito.when(apiLogBaseMapper.selectByPrimaryKey(Mockito.any())).thenReturn(apiLogEntity);
		ApiLogEntity resp = postJson("/aaa/queryAaaById", 1, new TypeReference<ApiLogEntity>() {
		});
		Assertions.assertThat(resp).isEqualTo(apiLogEntity);
	}

	public ApiLogEntity mockEntity() {
		return MockUtil.mock(ApiLogEntity.class);
	}
	
}