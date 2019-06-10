package com.liyulin.mocktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.mocktest.biz.AppLogBiz;
import com.liyulin.mocktest.entity.ApiLogEntity;

@Service
public class AppLogService {
	
	@Autowired
	private AppLogBiz appLogBiz;
	
	public ApiLogEntity queryById(Long id) {
		return appLogBiz.queryById(id);
	}
	
}