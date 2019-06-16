package com.liyulin.mocktest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.mocktest.biz.AppLogBiz;
import com.liyulin.mocktest.entity.ApiLogEntity;
import com.liyulin.mocktest.mapper.ApiLogBaseMapper;
import com.liyulin.mocktest.service.AppLogService;
import com.liyulin.mocktest.util.MockUtil;

public class MockitoTest extends AbstractUnitTest {
	
	@Test
	public void testQueryByIdWithMockMapper() throws Exception {
		ApiLogBaseMapper apiLogBaseMapper = Mockito.mock(ApiLogBaseMapper.class);
		AppLogBiz appLogBiz = applicationContext.getBean(AppLogBiz.class);
		initMocks(appLogBiz, apiLogBaseMapper);
		
		ApiLogEntity apiLogEntity = mockEntity();
		Mockito.when(apiLogBaseMapper.selectByPrimaryKey(Mockito.any())).thenReturn(apiLogEntity);
		ApiLogEntity resp = postJson("/appLog/queryById", 1, new TypeReference<ApiLogEntity>() {
		});
		Assertions.assertThat(resp).isEqualToComparingFieldByField(apiLogEntity);
	}
	
	@Test
	public void testQueryByIdWithMockBiz() throws Exception {
		AppLogBiz appLogBiz = Mockito.mock(AppLogBiz.class);
		AppLogService appLogService = applicationContext.getBean(AppLogService.class);
		initMocks(appLogService, appLogBiz);
		
		ApiLogEntity apiLogEntity = mockEntity();
		Mockito.when(appLogBiz.queryById(Mockito.any())).thenReturn(apiLogEntity);
		ApiLogEntity resp = postJson("/appLog/queryById", 1, new TypeReference<ApiLogEntity>() {
		});
		Assertions.assertThat(resp).isEqualToComparingFieldByField(apiLogEntity);
	}
	
	private ApiLogEntity mockEntity() {
		return MockUtil.mock(ApiLogEntity.class);
	}
	
}