package com.liyulin.mock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.mock.biz.AppLogBiz;
import com.liyulin.mock.entity.ApiLogEntity;

@Service
public class AppLogService {
	
	@Autowired
	private AppLogBiz appLogBiz;
	
	public ApiLogEntity queryById(Long id) {
		return appLogBiz.queryById(id);
	}
	
}