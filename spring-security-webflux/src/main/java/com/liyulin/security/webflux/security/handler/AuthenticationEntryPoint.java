package com.liyulin.security.webflux.security.handler;

import com.liyulin.security.webflux.enums.ReturnCode;
import com.liyulin.security.webflux.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        log.warn("authentication.entry.point", e);
        return SecurityUtil.withErrorResponse(ReturnCode.AUTHORIZATION_DENIED, exchange.getResponse());
    }

}