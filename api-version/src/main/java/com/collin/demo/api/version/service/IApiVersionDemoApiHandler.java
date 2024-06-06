package com.collin.demo.api.version.service;

import com.collin.demo.api.version.annotation.ApiHandlerMethod;
import com.collin.demo.api.version.vo.ApiVersionDemoRequestVO;

public interface IApiVersionDemoApiHandler {

    @ApiHandlerMethod
    String test(ApiVersionDemoRequestVO apiVersionDemoRequestVO);

}