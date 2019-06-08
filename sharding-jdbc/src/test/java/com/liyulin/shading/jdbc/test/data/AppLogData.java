package com.liyulin.shading.jdbc.test.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.shading.jdbc.entity.ApiLogEntity;
import com.liyulin.shading.jdbc.enums.DelStateEnum;
import com.liyulin.shading.jdbc.mapper.ApiLogBaseMapper;

@Service
public class AppLogData {

	@Autowired
	private ApiLogBaseMapper apiLogBaseMapper;

	public boolean insert(String addTimeStr) {
		ApiLogEntity entity = new ApiLogEntity();
		entity.setUrl("https://www.baidu.com");
		entity.setHttpMethod("POST");

		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		Date addTime = format.parseDateTime(addTimeStr).toDate();
		entity.setAddTime(addTime);
		entity.setDelState(DelStateEnum.NORMAL.getDelState());

		return apiLogBaseMapper.insertSelective(entity) > 0;
	}

	public void batchInsert(String addTimeStr, int size) {
		List<ApiLogEntity> recordList = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			ApiLogEntity entity = new ApiLogEntity();
			entity.setUrl("https://www.baidu.com");
			entity.setHttpMethod("POST");

			DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			Date addTime = format.parseDateTime(addTimeStr).toDate();
			entity.setAddTime(addTime);
			entity.setDelState(DelStateEnum.NORMAL.getDelState());

			recordList.add(entity);
		}

		apiLogBaseMapper.insertList(recordList);
	}

}