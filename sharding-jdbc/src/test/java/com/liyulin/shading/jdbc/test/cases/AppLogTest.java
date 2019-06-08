package com.liyulin.shading.jdbc.test.cases;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.shading.jdbc.base.BaseEntity;
import com.liyulin.shading.jdbc.entity.ApiLogEntity;
import com.liyulin.shading.jdbc.enums.DelStateEnum;
import com.liyulin.shading.jdbc.mapper.ApiLogBaseMapper;
import com.liyulin.shading.jdbc.test.data.AppLogData;
import com.liyulin.shading.jdbc.uitl.DbTableUtil;
import com.liyulin.shading.jdbc.uitl.WeekShardingUtil;

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
	private String startTime = "2019-06-01 00:00:00";
	private String endTime = "2019-06-03 00:00:00";

	@Before
	public void setBefore() {
		String logicTableName = WeekShardingUtil.getLogicTableName(ApiLogEntity.class);
		DbTableUtil.cleanTable(logicTableName, sqlSessionFactory);

		String actualTableName22 = WeekShardingUtil.getActualTableName(WeekShardingUtil.parse(startTime),
				logicTableName);
		String actualTableName23 = WeekShardingUtil.getActualTableName(WeekShardingUtil.parse(endTime), logicTableName);
		// 创建表
		DbTableUtil.createTableIfAbsent(logicTableName, actualTableName22, sqlSessionFactory);
		DbTableUtil.createTableIfAbsent(logicTableName, actualTableName23, sqlSessionFactory);
	}

	@Test
	public void testInsert() {
		Assertions.assertThat(appLogData.insert("2019-06-02 00:00:00")).isTrue();
	}

	@Test
	public void testPage() {
		// 插入数据
		appLogData.batchInsert(startTime, 8);
		appLogData.batchInsert(endTime, 8);

		Example example = new Example(ApiLogEntity.class);
		example.createCriteria().andBetween(BaseEntity.Columns.ADD_TIME.getProperty(), startTime, endTime)
				.andEqualTo(BaseEntity.Columns.DEL_STATE.getProperty(), DelStateEnum.NORMAL.getDelState());
		// 分页查询
		int pageIndex = 1;
		int pageSize = 10;
		int start = (pageIndex - 1) * pageSize;
		int end = pageSize;
		List<ApiLogEntity> list = apiLogBaseMapper.selectByExampleAndRowBounds(example, new RowBounds(start, end));

		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(pageSize);
	}

}