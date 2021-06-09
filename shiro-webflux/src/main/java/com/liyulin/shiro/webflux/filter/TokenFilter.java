package com.liyulin.shiro.webflux.filter;

import com.liyulin.shiro.webflux.constants.GateWayOrder;
import com.liyulin.shiro.webflux.entity.User;
import com.liyulin.shiro.webflux.service.impl.UserCache;
import com.liyulin.shiro.webflux.service.impl.UserContext;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author collin
 * @date 2021-06-09
 */
public class TokenFilter implements WebFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain chain) {
        String token = getFromRequest(serverWebExchange.getRequest(), "token");
        if (token != null && token.trim().length() > 0) {
            User user = UserCache.getUserCache().get(token);
            UserContext.setUser(user);
        }
        return chain.filter(serverWebExchange);
    }

    private static String getFromRequest(ServerHttpRequest request, String name) {
        String value = request.getHeaders().getFirst(name);
        if (value == null || value.trim().length() == 0) {
            // 如果请求头中不包含授权信息则从Query中获取参数
            value = request.getQueryParams().getFirst(name.toLowerCase());
        }
        return value;
    }

    @Override
    public int getOrder() {
        return GateWayOrder.TOKEN;
    }

}