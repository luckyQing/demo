package com.liyulin.mock.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liyulin.mock.entity.ApiLogEntity;
import com.liyulin.mock.mapper.ApiLogBaseMapper;

@Repository
public class AppLogBiz {

	@Autowired
	private ApiLogBaseMapper apiLogBaseMapper;
	
	public ApiLogEntity queryById(Long id) {
		return apiLogBaseMapper.selectByPrimaryKey(id);
	}
	
}