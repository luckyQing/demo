package com.liyulin.shading.jdbc.test.data;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.shading.jdbc.demo.entity.ApiLogEntity;
import com.liyulin.shading.jdbc.demo.enums.DelStateEnum;
import com.liyulin.shading.jdbc.demo.mapper.ApiLogBaseMapper;

@Service
public class AppLogData {

	@Autowired
	private ApiLogBaseMapper apiLogBaseMapper;

	public void testInsert(String addTimeStr) {
		ApiLogEntity entity = new ApiLogEntity();
		entity.setUrl("https://www.baidu.com");
		entity.setHttpMethod("POST");

		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		Date addTime = format.parseDateTime(addTimeStr).toDate();
		entity.setAddTime(addTime);
		entity.setDelState(DelStateEnum.NORMAL.getDelState());
		int count = apiLogBaseMapper.insertSelective(entity);

		Assertions.assertThat(count).isEqualTo(1);
	}

}