package com.liyulin.redis.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class HotWordService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	private static final String HOT_WORD_KEY = "hw";

	public boolean save(String hotWord) {
		stringRedisTemplate.opsForZSet().incrementScore(HOT_WORD_KEY, hotWord, 1);
		return true;
	}

	public Set<String> query() {
		Set<TypedTuple<String>> set = stringRedisTemplate.opsForZSet().reverseRangeWithScores(HOT_WORD_KEY, 0, 10);
		if (CollectionUtils.isEmpty(set)) {
			return new HashSet<>();
		}
		return set.stream().map(word -> word.getValue()).collect(Collectors.toSet());
	}

}