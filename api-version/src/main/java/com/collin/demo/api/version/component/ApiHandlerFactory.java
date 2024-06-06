package com.collin.demo.api.version.component;

import com.collin.demo.api.version.annotation.ApiHandlerMethod;
import com.collin.demo.api.version.annotation.ApiHandlerVersion;
import com.collin.demo.api.version.dto.ApiHandlerDTO;
import com.collin.demo.api.version.exception.ApiHandlerMethodMissingException;
import com.collin.demo.api.version.exception.ApiHandlerNotFoundException;
import com.collin.demo.api.version.exception.DuplicateApiHandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ApiHandlerFactory implements ApplicationContextAware, SmartInitializingSingleton {

    private ApplicationContext applicationContext;
    private Map<String, ApiHandlerDTO> apiVersionBeanRoute = new HashMap<>();

    public <T> T handle(String apiName, String apiVersion, Object[] params) {
        String routeKey = buildRouteKey(apiName, apiVersion);
        ApiHandlerDTO apiHandlerDTO = apiVersionBeanRoute.get(routeKey);
        if (apiHandlerDTO == null) {
            throw new ApiHandlerNotFoundException(apiName + ":" + apiVersion);
        }

        try {
            return (T) apiHandlerDTO.getHandlerMethod().invoke(apiHandlerDTO.getHandler(), params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(ApiHandlerVersion.class);
        if (beanMap.isEmpty()) {
            log.warn("bean with ApiHandlerVersion annotation is empty!");
            return;
        }

        Collection<Object> beans = beanMap.values();
        for (Object bean : beans) {
            Class<?> beanClass = bean.getClass();
            ApiHandlerVersion apiHandlerVersion = AnnotationUtils.findAnnotation(beanClass, ApiHandlerVersion.class);
            String routeKey = buildRouteKey(apiHandlerVersion.routeKeyPrefix(), "v" + apiHandlerVersion.version());
            Method apiHanlerMethod = getApiHandleMethod(beanClass, apiHandlerVersion);

            check(beanClass, routeKey);

            apiVersionBeanRoute.put(routeKey, new ApiHandlerDTO(bean, apiHanlerMethod));
        }
    }

    private String buildRouteKey(String routeKeyPrefix, String version) {
        return routeKeyPrefix + version;
    }

    private void check(Class<?> beanClass, String routeKey) {
        // 是否已存在
        if (apiVersionBeanRoute.containsKey(routeKey)) {
            throw new DuplicateApiHandlerException(beanClass.getName());
        }
    }

    private Method getApiHandleMethod(Class<?> beanClass, ApiHandlerVersion apiHandlerVersion) {
        String methodName = apiHandlerVersion.method();
        Method[] apiHandlerMethods = beanClass.getMethods();

        // 优先ApiMethod注解匹配
        for (Method apiHandlerMethod : apiHandlerMethods) {
            if (AnnotationUtils.findAnnotation(apiHandlerMethod, ApiHandlerMethod.class) != null) {
                return apiHandlerMethod;
            }
        }

        // ApiHandlerVersion#method指定名字匹配
        for (Method apiHandlerMethod : apiHandlerMethods) {
            if (apiHandlerMethod.getName().equals(methodName)) {
                return apiHandlerMethod;
            }
        }

        throw new ApiHandlerMethodMissingException(beanClass.getName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}