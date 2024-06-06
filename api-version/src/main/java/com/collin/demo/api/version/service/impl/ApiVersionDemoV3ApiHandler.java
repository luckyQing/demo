package com.collin.demo.api.version.service.impl;

import com.collin.demo.api.version.annotation.ApiHandlerMethod;
import com.collin.demo.api.version.annotation.ApiHandlerVersion;
import com.collin.demo.api.version.constants.ApiHandlerNames;
import com.collin.demo.api.version.vo.ApiVersionDemoRequestVO;
import org.springframework.stereotype.Component;

@Component
@ApiHandlerVersion(routeKeyPrefix = ApiHandlerNames.API_VERSION_DEMO_API_HANDLER, version = 3)
public class ApiVersionDemoV3ApiHandler {

    @ApiHandlerMethod
    public String process(ApiVersionDemoRequestVO apiVersionDemoRequestVO) {
        return "v3";
    }

}