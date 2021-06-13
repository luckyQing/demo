package com.liyulin.security.webflux.configure;

import com.liyulin.security.webflux.constants.SecurityConstants;
import com.liyulin.security.webflux.security.handler.AuthenticationEntryPoint;
import com.liyulin.security.webflux.security.handler.AuthenticationFailHandler;
import com.liyulin.security.webflux.security.handler.AuthenticationSuccessHandler;
import com.liyulin.security.webflux.security.handler.AuthorizationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * security配置
 *
 * @author collin
 * @date 2021-06-11
 */
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class WebfluxSecurityAutoConfiguration {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailHandler authenticationFailHandler;
    @Autowired
    private AuthorizationAccessDeniedHandler authorizationAccessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                // 除了注解标记的接口以外。其余接口都允许访问
                .anyExchange().permitAll()
                .and()
                .httpBasic()
                .and()
                .formLogin().loginPage(SecurityConstants.LOGIN_URL)
                // 登录成功
                .authenticationSuccessHandler(authenticationSuccessHandler)
                // 登录失败
                .authenticationFailureHandler(authenticationFailHandler)
                .and().exceptionHandling()
                // 无访问权限
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(authorizationAccessDeniedHandler)
                .and().csrf().disable()
                .logout().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //默认
    }

}