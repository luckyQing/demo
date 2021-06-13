package com.liyulin.security.webflux.security.handler;

import com.liyulin.security.webflux.enums.ReturnCode;
import com.liyulin.security.webflux.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 无权限访问
 *
 * @author collin
 * @date 2021-06-16
 */
@Component
@Slf4j
public class AuthorizationAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        log.warn("authorization.access.denied", denied);
        return SecurityUtil.withErrorResponse(ReturnCode.AUTHORIZATION_DENIED, exchange.getResponse());
    }

}