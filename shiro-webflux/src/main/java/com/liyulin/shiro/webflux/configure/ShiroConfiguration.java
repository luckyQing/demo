package com.liyulin.shiro.webflux.configure;

import com.liyulin.shiro.webflux.service.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public DefaultSecurityManager securityManager(final UserRealm userRealm) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(userRealm);

        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

}