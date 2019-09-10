package com.liyulin.mocktest.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class StaticService {

	public boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}
	
}