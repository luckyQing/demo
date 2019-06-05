package com.liyulin.shading.jdbc.demo;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.shading.jdbc.demo.base.BaseEntity;
import com.liyulin.shading.jdbc.demo.entity.ApiLogEntity;
import com.liyulin.shading.jdbc.demo.enums.DelStateEnum;
import com.liyulin.shading.jdbc.demo.mapper.ApiLogBaseMapper;

import tk.mybatis.mapper.entity.Example;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired
	private ApiLogBaseMapper apiLogBaseMapper;

	@Before
	public void setBefore() {
//		Example example = new Example(ApiLogEntity.class);
//		example.createCriteria().andBetween(BaseEntity.Columns.ADD_TIME.getProperty(), "2019-06-03 00:00:00", "2019-06-05 00:00:00");
//		apiLogBaseMapper.deleteByExample(example);
	}

	@Test
	public void testInsert() {
		ApiLogEntity entity = new ApiLogEntity();
		entity.setId(2000L);
		entity.setUrl("https://www.baidu.com");
		entity.setHttpMethod("POST");
		entity.setAddTime(new Date());
		entity.setDelState(DelStateEnum.NORMAL.getDelState());
		int count = apiLogBaseMapper.insertSelective(entity);

		Assertions.assertThat(count).isEqualTo(1);
	}

	@Test
	public void testSelect() {
		Example example = new Example(ApiLogEntity.class);
		example.createCriteria().andBetween(BaseEntity.Columns.ADD_TIME.getProperty(), "2019-06-03 00:00:00",
				"2019-06-05 00:00:00");
		List<ApiLogEntity> list = apiLogBaseMapper.selectByExample(null);
	}

}