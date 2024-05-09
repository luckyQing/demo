package com.liyulin.demo.gateway.function;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class RewriteRequestBodyFunction implements RewriteFunction<byte[], byte[]> {

    @Override
    public Publisher<byte[]> apply(ServerWebExchange exchange, byte[] content) {
        // request
        if (!ServerWebExchangeUtils.isAlreadyRouted(exchange)) {
            MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
            if (mediaType != null && mediaType.includes(MediaType.APPLICATION_JSON)) {
                String requestBody = new String(content);
                log.warn("{}---->requestBody={}", exchange.getRequest().getPath(), requestBody);
            }
        }


        return Mono.just(content);
    }

}