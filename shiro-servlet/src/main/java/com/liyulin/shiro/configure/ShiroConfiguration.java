package com.liyulin.shiro.configure;

import com.liyulin.shiro.service.shiro.RedisSessionDAO;
import com.liyulin.shiro.service.shiro.ShiroTokenManager;
import com.liyulin.shiro.service.shiro.UserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(final RedissonClient redissonClient) {
        return new RedisSessionDAO(redissonClient);
    }

    @Bean
    public ShiroTokenManager shiroTokenManager(final RedisSessionDAO redisSessionDAO) {
        ShiroTokenManager shiroTokenManager = new ShiroTokenManager();
        shiroTokenManager.setSessionIdCookieEnabled(false);
        shiroTokenManager.setSessionDAO(redisSessionDAO);
//        shiroTokenManager.setSessionIdUrlRewritingEnabled(false);
        return shiroTokenManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(final UserRealm userRealm, final ShiroTokenManager shiroTokenManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(shiroTokenManager);
        return securityManager;
    }

    /**
     * 路径过滤规则
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(final DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");

        /*Map<String, String> map = new LinkedHashMap<>(2);
        // 有先后顺序
        // 允许匿名访问
        map.put("/login", "anon");
        map.put("/favicon.ico", "anon");
        // 进行身份认证后才能访问
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);*/
        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro注解模式，可以在Controller中的方法上添加注解
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

}