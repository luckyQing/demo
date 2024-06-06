package com.collin.demo.api.version.controller;

import com.collin.demo.api.version.component.ApiHandlerFactory;
import com.collin.demo.api.version.constants.ApiHandlerNames;
import com.collin.demo.api.version.vo.ApiVersionDemoRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * url匹配
 *
 * @author collin
 * @date 2024-05-30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("client/url")
public class UrlController {

    private final ApiHandlerFactory apiHandlerFactory;

    @PostMapping(value = "/{version:v\\d+}/test")
    public ResponseEntity<String> testV2(@PathVariable(name = "version") String version, @RequestBody ApiVersionDemoRequestVO requestVO) {
        return ResponseEntity.ok(apiHandlerFactory.handle(ApiHandlerNames.API_VERSION_DEMO_API_HANDLER, version, new Object[]{requestVO}));
    }

}