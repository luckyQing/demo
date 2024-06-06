package com.collin.demo.api.version.service.impl;

import com.collin.demo.api.version.annotation.ApiHandlerVersion;
import com.collin.demo.api.version.constants.ApiHandlerNames;
import com.collin.demo.api.version.service.IApiVersionDemoApiHandler;
import com.collin.demo.api.version.vo.ApiVersionDemoRequestVO;
import org.springframework.stereotype.Component;

@Component
@ApiHandlerVersion(routeKeyPrefix = ApiHandlerNames.API_VERSION_DEMO_API_HANDLER, version = 1)
public class ApiVersionDemoV1ApiHandler implements IApiVersionDemoApiHandler {

    @Override
    public String test(ApiVersionDemoRequestVO apiVersionDemoRequestVO) {
        return "v1";
    }

}