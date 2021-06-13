package com.liyulin.security.webflux.security.handler;

import com.liyulin.security.webflux.enums.ReturnCode;
import com.liyulin.security.webflux.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 认证失败
 *
 * @author collin
 * @date 2021-06-16
 */
@Component
@Slf4j
public class AuthenticationFailHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
        log.warn("authentication.fail", e);
        return SecurityUtil.withErrorResponse(ReturnCode.AUTHENTICATION_FAIL, webFilterExchange.getExchange().getResponse());
    }

}