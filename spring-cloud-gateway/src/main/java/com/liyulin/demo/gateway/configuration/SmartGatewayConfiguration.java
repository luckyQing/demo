package com.liyulin.demo.gateway.configuration;

import com.liyulin.demo.gateway.filter.RewriteRequestFilter;
import com.liyulin.demo.gateway.filter.RewriteResponseFilter;
import com.liyulin.demo.gateway.function.RewriteRequestBodyFunction;
import com.liyulin.demo.gateway.function.RewriteResponseBodyFunction;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartGatewayConfiguration {

    @Bean
    public RewriteRequestBodyFunction rewriteRequestBodyFunction() {
        return new RewriteRequestBodyFunction();
    }

    @Bean
    public RewriteResponseBodyFunction rewriteResponseBodyFunction() {
        return new RewriteResponseBodyFunction();
    }

    @Bean
    public GlobalFilter rewriteRequestFilter(ModifyRequestBodyGatewayFilterFactory modifyRequestBodyGatewayFilterFactory, RewriteRequestBodyFunction rewriteRequestBodyFunction) {
        return new RewriteRequestFilter(modifyRequestBodyGatewayFilterFactory, rewriteRequestBodyFunction);
    }

    @Bean
    public GlobalFilter rewriteResponseFilter(ModifyResponseBodyGatewayFilterFactory modifyResponseBodyGatewayFilterFactory, RewriteResponseBodyFunction rewriteResponseBodyFunction) {
        return new RewriteResponseFilter(modifyResponseBodyGatewayFilterFactory, rewriteResponseBodyFunction);
    }

}