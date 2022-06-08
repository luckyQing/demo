package com.liyulin.shiro.service.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author collin
 * @date 2022-06-07
 */
@Slf4j
public class ShiroTokenManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        if (token == null || token.trim().length() == 0) {
            token = request.getParameter("token");
        }

        if (token != null && token.trim().length() > 0) {
            log.info("token1={}", token);

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
            return token;
        }
        log.info("token2=null");
        return super.getSessionId(request, response);
    }

}