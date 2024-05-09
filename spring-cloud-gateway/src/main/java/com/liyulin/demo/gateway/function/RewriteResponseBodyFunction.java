package com.liyulin.demo.gateway.function;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class RewriteResponseBodyFunction implements RewriteFunction<byte[], byte[]> {

    @Override
    public Publisher<byte[]> apply(ServerWebExchange exchange, byte[] content) {
        MediaType mediaType = exchange.getResponse().getHeaders().getContentType();
        if (mediaType != null && mediaType.includes(MediaType.APPLICATION_JSON)) {
            String responseBody = new String(content);
            log.warn("{}---->requestBody={}", exchange.getRequest().getPath(), responseBody);
        }

        return Mono.just(content);
    }

}