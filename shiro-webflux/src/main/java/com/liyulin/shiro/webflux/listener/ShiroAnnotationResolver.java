package com.liyulin.shiro.webflux.listener;

import com.liyulin.shiro.webflux.bo.ShiroMeta;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.*;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * shiro注解解析
 *
 * @author collin
 * @date 2021-06-07
 */
@Component
@Slf4j
public class ShiroAnnotationResolver implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

    }

    public Map<String, ShiroMeta> collectApiMetas() {
        Reflections reflections = new Reflections("com.liyulin", new MethodAnnotationsScanner());
        Set<Method> allMappingSet = new HashSet<>();
        allMappingSet.addAll(reflections.getMethodsAnnotatedWith(RequiresPermissions.class));
        allMappingSet.addAll(reflections.getMethodsAnnotatedWith(RequiresRoles.class));
        allMappingSet.addAll(reflections.getMethodsAnnotatedWith(RequiresUser.class));
        allMappingSet.addAll(reflections.getMethodsAnnotatedWith(RequiresGuest.class));
        allMappingSet.addAll(reflections.getMethodsAnnotatedWith(RequiresAuthentication.class));

        if (CollectionUtils.isEmpty(allMappingSet)) {
            log.debug("shiro methodSet is empty!");
            return null;
        }

        Map<String, ShiroMeta> shiroMetaMap = new HashMap<>();
        for (Method method : allMappingSet) {
            Class<?> declaringClass = method.getDeclaringClass();
        }
        return shiroMetaMap;
    }
}