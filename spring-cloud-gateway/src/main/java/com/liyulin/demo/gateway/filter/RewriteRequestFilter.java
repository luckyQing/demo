package com.liyulin.demo.gateway.filter;

import com.liyulin.demo.gateway.function.RewriteRequestBodyFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RewriteRequestFilter implements InitializingBean, GlobalFilter, Ordered {

    private final ModifyRequestBodyGatewayFilterFactory modifyRequestBodyGatewayFilterFactory;
    private final RewriteRequestBodyFunction rewriteRequestBodyFunction;
    private GatewayFilter delegate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return delegate.filter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ModifyRequestBodyGatewayFilterFactory.Config rewriteRequestBodyFunctionConfig = new ModifyRequestBodyGatewayFilterFactory.Config()
                .setRewriteFunction(byte[].class, byte[].class, rewriteRequestBodyFunction);
        this.delegate = modifyRequestBodyGatewayFilterFactory.apply(rewriteRequestBodyFunctionConfig);
    }
}