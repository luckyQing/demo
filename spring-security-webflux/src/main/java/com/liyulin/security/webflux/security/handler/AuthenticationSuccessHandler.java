package com.liyulin.security.webflux.security.handler;

import com.liyulin.security.webflux.constants.SecurityConstants;
import com.liyulin.security.webflux.pojo.bo.SecurityUserDetails;
import com.liyulin.security.webflux.pojo.vo.RespVO;
import com.liyulin.security.webflux.util.SecurityUtil;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 认证成功
 *
 * @author collin
 * @date 2021-06-16
 */
@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        SecurityUserDetails authUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.getHeaders().add(SecurityConstants.TOKEN, authUserDetails.getToken());
        return SecurityUtil.withSuccessResponse(RespVO.success(authUserDetails.getToken()), response);
    }

}