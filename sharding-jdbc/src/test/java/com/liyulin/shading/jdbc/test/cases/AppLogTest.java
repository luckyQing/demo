package com.liyulin.shading.jdbc.test.cases;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
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
import com.liyulin.shading.jdbc.demo.uitl.DbTableUtil;
import com.liyulin.shading.jdbc.demo.uitl.WeekShardingUtil;
import com.liyulin.shading.jdbc.test.data.AppLogData;

import tk.mybatis.mapper.entity.Example;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppLogTest {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private ApiLogBaseMapper apiLogBaseMapper;
	@Autowired
	private AppLogData appLogData;

	@Before
	public void setBefore() {
		String logicTableName = WeekShardingUtil.getLogicTableName(ApiLogEntity.class);
		DbTableUtil.cleanTable(logicTableName, sqlSessionFactory);
	}
	
	@Test
	public void testInsert() {
		ApiLogEntity entity = new ApiLogEntity();
		entity.setUrl("https://www.baidu.com");
		entity.setHttpMethod("POST");
		entity.setAddTime(new Date());
		entity.setDelState(DelStateEnum.NORMAL.getDelState());
		int count = apiLogBaseMapper.insertSelective(entity);

		Assertions.assertThat(count).isEqualTo(1);
	}

	@Test
	public void testSelect() {
		String startTime = "2019-06-01 00:00:00";
		String endTime = "2019-06-03 00:00:00";
		
		String logicTableName = WeekShardingUtil.getLogicTableName(ApiLogEntity.class);
		String actualTableName22 = WeekShardingUtil.getActualTableName(WeekShardingUtil.parse(startTime), logicTableName);
		String actualTableName23 = WeekShardingUtil.getActualTableName(WeekShardingUtil.parse(endTime), logicTableName);
		// 创建表
		DbTableUtil.createTableIfAbsent(logicTableName, actualTableName22, sqlSessionFactory);
		DbTableUtil.createTableIfAbsent(logicTableName, actualTableName23, sqlSessionFactory);
		// 清空数据
		DbTableUtil.delete(actualTableName22, sqlSessionFactory);
		DbTableUtil.delete(actualTableName23, sqlSessionFactory);
		// 插入数据
		appLogData.testInsert(startTime);
		appLogData.testInsert(endTime);
		
		Example example = new Example(ApiLogEntity.class);
		example.createCriteria().andBetween(BaseEntity.Columns.ADD_TIME.getProperty(), startTime, endTime);
		List<ApiLogEntity> list = apiLogBaseMapper.selectByExample(example);
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(2);
	}

}