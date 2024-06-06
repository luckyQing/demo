package com.collin.demo.api.version.service.impl;

import com.collin.demo.api.version.annotation.ApiHandlerVersion;
import com.collin.demo.api.version.constants.ApiHandlerNames;
import com.collin.demo.api.version.vo.ApiVersionDemoRequestVO;
import org.springframework.stereotype.Component;

@Component
@ApiHandlerVersion(routeKeyPrefix = ApiHandlerNames.API_VERSION_DEMO_API_HANDLER, version = 4)
public class ApiVersionDemoV4ApiHandler {

    public String handler(ApiVersionDemoRequestVO apiVersionDemoRequestVO) {
        return "v4";
    }

}