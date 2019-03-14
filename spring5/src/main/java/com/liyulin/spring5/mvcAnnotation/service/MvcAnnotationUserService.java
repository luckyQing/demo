package com.liyulin.spring5.mvcAnnotation.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.liyulin.spring5.dto.req.UserReqBody;
import com.liyulin.spring5.dto.resp.UserRespBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MvcAnnotationUserService {

	public static final ConcurrentMap<Long, UserRespBody> DATA = new ConcurrentHashMap<>();
	private static final AtomicLong ID_GENERATOR = new AtomicLong(0);
	
	public Mono<UserRespBody> user(long id) {
		return Mono.create(sink -> {
			sink.success(DATA.get(id));
		});
	}

	public Flux<UserRespBody> users() {
		return Flux.create(sink -> {
			DATA.forEach((id, user) -> {
				sink.next(user);
			});

			sink.complete();
		});
	}

	public Mono<Boolean> create(UserReqBody reqBody) {
		return Mono.create(sink -> {
			UserRespBody userRespBody = UserRespBody.builder()
					.id(ID_GENERATOR.addAndGet(1))
					.name(reqBody.getName())
					.age(reqBody.getAge())
					.address(reqBody.getAddress())
					.build();

			DATA.put(userRespBody.getId(), userRespBody);

			sink.success(true);
		});
	}

}