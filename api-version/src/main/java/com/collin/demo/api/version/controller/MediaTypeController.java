package com.collin.demo.api.version.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过Content-Type控制版本
 *
 * @author collin
 * @date 2024-05-30
 */
@RestController
@RequestMapping("client/media-type")
public class MediaTypeController {

    @GetMapping(value = "test", produces = "application/vnd.api-v1+json")
    public ResponseEntity<String> testV1() {
        return ResponseEntity.ok("v1");
    }

    @GetMapping(value = "test", produces = "application/vnd.api-v2+json")
    public ResponseEntity<String> testV2() {
        return ResponseEntity.ok("v2");
    }

}