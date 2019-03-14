package com.liyulin.spring5.functional.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.liyulin.spring5.dto.req.UserReqBody;
import com.liyulin.spring5.dto.resp.UserRespBody;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FunctionalUserService {

	private static final ConcurrentMap<Long, UserRespBody> DATA = new ConcurrentHashMap<>();
	private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

	public Mono<ServerResponse> create(ServerRequest request) {
		Mono<UserReqBody> userReqBodyMono = request.bodyToMono(UserReqBody.class);
		log.info("userReqBody=>{}", userReqBodyMono.block());
		userReqBodyMono.subscribe(userReqBody->{
			UserRespBody userRespBody = UserRespBody.builder()
					.id(ID_GENERATOR.addAndGet(1))
					.name(userReqBody.getName())
					.age(userReqBody.getAge())
					.address(userReqBody.getAddress())
					.build();
			DATA.put(userRespBody.getId(), userRespBody);
		});
		return ServerResponse.ok().syncBody(true);
	}

	public Mono<ServerResponse> users(ServerRequest request) {
		Flux<UserRespBody> userFlux = Flux.create(sink -> {
			DATA.forEach((id, user) -> {
				sink.next(user);
			});

			sink.complete();
		});
		
		return ServerResponse.ok().body(BodyInserters.fromPublisher(userFlux, UserRespBody.class));
	}
	
	public Mono<ServerResponse> user(ServerRequest request) {
		Mono<UserRespBody> userRespBodyMono = Mono.create(sink -> {
			long id = Long.valueOf(request.pathVariable("id"));
			sink.success(DATA.get(id));
		});

		return ServerResponse.ok().body(userRespBodyMono, UserRespBody.class);
	}

}