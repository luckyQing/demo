package com.liyulin.demo.gateway.filter;

import com.liyulin.demo.gateway.function.RewriteResponseBodyFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RewriteResponseFilter implements InitializingBean, GlobalFilter, Ordered {

    private final ModifyResponseBodyGatewayFilterFactory modifyResponseBodyGatewayFilterFactory;
    private final RewriteResponseBodyFunction rewriteResponseBodyFunction;
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
        ModifyResponseBodyGatewayFilterFactory.Config rewriteResponseBodyFunctionConfig = new ModifyResponseBodyGatewayFilterFactory.Config()
                .setRewriteFunction(byte[].class, byte[].class, rewriteResponseBodyFunction);
        this.delegate = modifyResponseBodyGatewayFilterFactory.apply(rewriteResponseBodyFunctionConfig);
    }

}