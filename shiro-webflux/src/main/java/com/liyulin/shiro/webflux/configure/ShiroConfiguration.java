package com.liyulin.shiro.webflux.configure;

import com.liyulin.shiro.webflux.service.shiro.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ShiroConfiguration implements ApplicationContextAware {

    private static final String SECURITY_MANAGER_BEAN_NAME = "securityManager";

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean(SECURITY_MANAGER_BEAN_NAME)
    public DefaultSecurityManager securityManager(final UserRealm userRealm) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext.containsBean(SECURITY_MANAGER_BEAN_NAME)) {
            DefaultSecurityManager securityManager = applicationContext.getBean(DefaultSecurityManager.class);
            SecurityUtils.setSecurityManager(securityManager);
            log.debug("SecurityUtils set securityManager finished!");
        }
    }
}