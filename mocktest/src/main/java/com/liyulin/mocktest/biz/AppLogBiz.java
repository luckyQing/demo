package com.liyulin.mocktest.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liyulin.mocktest.entity.ApiLogEntity;
import com.liyulin.mocktest.mapper.ApiLogBaseMapper;

@Repository
public class AppLogBiz {

	@Autowired
	private ApiLogBaseMapper apiLogBaseMapper;
	
	public ApiLogEntity queryById(Long id) {
		return apiLogBaseMapper.selectByPrimaryKey(id);
	}
	
}