package com.liyulin.shiro.webflux.filter;

import com.liyulin.shiro.webflux.bo.ShiroMeta;
import com.liyulin.shiro.webflux.constants.GateWayOrder;
import com.liyulin.shiro.webflux.service.impl.ShiroCache;
import com.liyulin.shiro.webflux.service.impl.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author collin
 * @date 2021-06-07
 */
public class ShiroFilter implements WebFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String urlMethod = request.getURI().getPath() + request.getMethodValue();

        ShiroMeta shiroMeta = ShiroCache.getShiroMetaCache().get(urlMethod);
        if (shiroMeta == null || (shiroMeta.getRequiresGuest() != null && shiroMeta.getRequiresGuest())) {
            return chain.filter(exchange);
        }

        if ((shiroMeta.getRequiresAuthentication() != null && shiroMeta.getRequiresAuthentication()) ||
                (shiroMeta.getRequiresUser() != null && shiroMeta.getRequiresUser())) {
            if (UserContext.getUser() == null) {
                throw new UnauthenticatedException();
            }
        }

        if (shiroMeta.getRoles() != null && shiroMeta.getRoles().length > 0) {
        }
        if (shiroMeta.getPermissions() != null && shiroMeta.getPermissions().length > 0) {

        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return GateWayOrder.SHIRO;
    }

}