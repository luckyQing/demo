package com.liyulin.mocktest;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.mocktest.biz.AppLogBiz;
import com.liyulin.mocktest.entity.ApiLogEntity;
import com.liyulin.mocktest.mapper.ApiLogBaseMapper;
import com.liyulin.mocktest.service.AppLogService;
import com.liyulin.mocktest.util.MockUtil;

public class ApiLogControllerTest extends AbstractUnitTest {

	@Mock
	private ApiLogBaseMapper apiLogBaseMapper;
	@InjectMocks
	@Autowired
	@Spy
	private AppLogBiz appLogBiz;
//	@InjectMocks
//	@Autowired
//	private AppLogService appLogService;
	
	@Before
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testQueryAaaByIdWithMockMapper() throws Exception {
//		ApiLogBaseMapper apiLogBaseMapper = Mockito.mock(ApiLogBaseMapper.class);
//		AppLogBiz appLogBiz = applicationContext.getBean(AppLogBiz.class);
		
		ApiLogEntity apiLogEntity = mockEntity();
		Mockito.when(apiLogBaseMapper.selectByPrimaryKey(Mockito.any())).thenReturn(apiLogEntity);
		ApiLogEntity resp = postJson("/aaa/queryAaaById", 1, new TypeReference<ApiLogEntity>() {
		});
		Assertions.assertThat(resp).isEqualTo(apiLogEntity);
	}
	
	@Test
	public void testQueryAaaByIdWithMockBiz() throws Exception {
		ApiLogEntity apiLogEntity = mockEntity();
		Mockito.when(appLogBiz.queryById(Mockito.any())).thenReturn(apiLogEntity);
		ApiLogEntity resp = postJson("/aaa/queryAaaById", 1, new TypeReference<ApiLogEntity>() {
		});
		Assertions.assertThat(resp).isEqualTo(apiLogEntity);
	}

	private ApiLogEntity mockEntity() {
		return MockUtil.mock(ApiLogEntity.class);
	}
	
}