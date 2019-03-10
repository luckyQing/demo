package com.liyulin.spring5.functional.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.liyulin.spring5.dto.req.UserReqBody;
import com.liyulin.spring5.dto.resp.UserRespBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FunctionalUserService {

	private static final ConcurrentMap<Long, UserRespBody> DATA = new ConcurrentHashMap<>();
	private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

	public Mono<ServerResponse> user(long id) {
		return ServerResponse.ok().syncBody(DATA.get(id));
	}

	public Mono<ServerResponse> users() {
		Flux<UserRespBody> userFlux = Flux.create(sink -> {
			DATA.forEach((id, user) -> {
				sink.next(user);
			});

			sink.complete();
		});
		return ServerResponse.ok().syncBody(userFlux);
	}

	public Mono<ServerResponse> create(UserReqBody reqBody) {
		UserRespBody userRespBody = UserRespBody.builder().id(ID_GENERATOR.addAndGet(1)).name(reqBody.getName())
				.age(reqBody.getAge()).address(reqBody.getAddress()).build();

		DATA.put(userRespBody.getId(), userRespBody);

		return ServerResponse.ok().syncBody(true);
	}
}