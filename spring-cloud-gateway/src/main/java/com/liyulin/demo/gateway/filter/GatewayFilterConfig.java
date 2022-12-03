package com.liyulin.demo.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

@Slf4j
//@Component
@RequiredArgsConstructor
public class GatewayFilterConfig implements WebFilter, Ordered {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        //5 修改请求参数,并获取请求参数
        Map<String, Object> paramMap = null;
        try {
            paramMap = updateRequestParam(exchange);
        } catch (Exception e) {
        }
        //6 获取请求体,修改请求体
        ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
            String encrypt = body;
            log.info("encrypt={}", encrypt);
            return Mono.just(encrypt);
        });

        //创建BodyInserter修改请求体

        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);

        //创建CachedBodyOutputMessage并且把请求param加入,初始化校验信息
        SecurityCachedBodyOutputMessage outputMessage = new SecurityCachedBodyOutputMessage(exchange, headers);
        outputMessage.setParamMap(paramMap);
        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> {
                    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            Flux<DataBuffer> body = outputMessage.getBody();
                            if (body.equals(Flux.empty())) {
                                //验证签名
                            }

                            return outputMessage.getBody();
                        }
                    };
                    return chain.filter(exchange.mutate().request(decorator).build());
                }));
    }

    /**
     * 修改前端传的参数
     */
    private Map<String, Object> updateRequestParam(ServerWebExchange exchange) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String query = uri.getQuery();
        if (query != null && query.length() > 0) {
            String[] kvStrs = query.split("&");
            Map<String, Object> kvs = new LinkedHashMap<>();
            Stream.of(kvStrs).forEach(kvStr -> {
                String[] kv = kvStr.split("=");
                kvs.put(kv[0], kv[1]);
            });
//            String param = RSAUtils.decrypt(split[1], RSAConstant.PRIVATE_KEY);
            log.info("param={}", objectMapper.writeValueAsString(kvs));
            /*Field targetQuery = uri.getClass().getDeclaredField("query");
            targetQuery.setAccessible(true);
            targetQuery.set(uri, param);*/
            return kvs;
        }
        return new LinkedHashMap<>(0);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}