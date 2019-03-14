package com.liyulin.spring5;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.liyulin.spring5.dto.req.UserReqBody;
import com.liyulin.spring5.dto.resp.UserRespBody;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MvcAnnotationControllerTest {
	private WebClient client = WebClient.create("http://localhost");

	@Test
	public void testCreate() {
		UserReqBody reqBody = UserReqBody.builder()
				.name("张三")
				.age(18)
				.address("深圳南山区科技园")
				.build();
		
		Mono<Boolean> resultMono = client.post()
				.uri("/mvc/user")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(reqBody))
				.retrieve()
				.bodyToMono(Boolean.class);

		Boolean result = resultMono.block();
		log.info("result={}", result);
		
		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void testUsers() {
		Flux<UserRespBody> result = client.get()
				.uri("mvc/user")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(UserRespBody.class);
		
		Assertions.assertThat(result).isNotNull();
		
		Iterable<UserRespBody> userIterable = result.toIterable();
		userIterable.forEach(item->{
			log.info("item={}", item);
		});
		Assertions.assertThat(userIterable).isNotNull();
	}
	
	@Test
	public void testUser() {
		Mono<UserRespBody> result = client.get()
				.uri("mvc/user/1")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(UserRespBody.class);
		
		Assertions.assertThat(result).isNotNull();
		UserRespBody userRespBody = result.block();
		log.info("result={}", userRespBody);
		Assertions.assertThat(userRespBody).isNotNull();
	}

}