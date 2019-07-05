package com.liyulin.mocktest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.mocktest.biz.AppLogBiz;
import com.liyulin.mocktest.controller.AppLogController;
import com.liyulin.mocktest.entity.ApiLogEntity;
import com.liyulin.mocktest.mapper.ApiLogBaseMapper;
import com.liyulin.mocktest.service.AppLogService;
import com.liyulin.mocktest.util.MockUtil;

public class MockitoTest extends AbstractUnitTest {

	/**
	 * mock mapper层
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQueryByIdWithMockMapper() throws Exception {
		ApiLogBaseMapper apiLogBaseMapper = Mockito.mock(ApiLogBaseMapper.class);
		AppLogBiz appLogBiz = applicationContext.getBean(AppLogBiz.class);
		setMockAttribute(appLogBiz, apiLogBaseMapper);

		ApiLogEntity apiLogEntity = mockEntity();
		Mockito.when(apiLogBaseMapper.selectByPrimaryKey(Mockito.any())).thenReturn(apiLogEntity);
		ApiLogEntity resp = postJson("/appLog/queryById", 1, new TypeReference<ApiLogEntity>() {
		});
		Assertions.assertThat(resp).isEqualToComparingFieldByField(apiLogEntity);
	}

	/**
	 * mock biz层
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQueryByIdWithMockBiz() throws Exception {
		AppLogBiz appLogBiz = Mockito.mock(AppLogBiz.class);
		AppLogService appLogService = applicationContext.getBean(AppLogService.class);
		setMockAttribute(appLogService, appLogBiz);

		ApiLogEntity apiLogEntity = mockEntity();
		Mockito.when(appLogBiz.queryById(Mockito.any())).thenReturn(apiLogEntity);
		ApiLogEntity resp = postJson("/appLog/queryById", 1, new TypeReference<ApiLogEntity>() {
		});
		Assertions.assertThat(resp).isEqualToComparingFieldByField(apiLogEntity);
	}

	/**
	 * mock部分方法
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRechargeWithPartialMethod() throws Exception {
		AppLogService appLogService = applicationContext.getBean(AppLogService.class);
		AppLogService appLogServiceSpy = Mockito.spy(appLogService);
		AppLogController appLogController = applicationContext.getBean(AppLogController.class);
		setMockAttribute(appLogController, appLogServiceSpy);

		Mockito.doReturn(true).when(appLogServiceSpy).doRemote();

		Boolean resp = postJson("/appLog/recharge", null, new TypeReference<Boolean>() {
		});
		Assertions.assertThat(resp).isTrue();
	}

	private ApiLogEntity mockEntity() {
		return MockUtil.mock(ApiLogEntity.class);
	}

}