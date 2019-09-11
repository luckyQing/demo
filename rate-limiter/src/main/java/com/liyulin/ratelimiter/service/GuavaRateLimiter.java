package com.liyulin.ratelimiter.service;

import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuavaRateLimiter {

	private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

	public String reduce(Long skuId) {
		if (!RATE_LIMITER.tryAcquire()) {
			log.warn("acquire access token fail...");
			return "fail";
		}
		
		// deal some business logic
		log.info("do something...");
		return "success";
	}

}