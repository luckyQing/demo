package com.liyulin.demo.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

@Getter
@Setter
public class SecurityCachedBodyOutputMessage extends CachedBodyOutputMessage {

    private Map<String, Object> paramMap;

    public SecurityCachedBodyOutputMessage(ServerWebExchange exchange, HttpHeaders httpHeaders) {
        super(exchange, httpHeaders);
    }

}