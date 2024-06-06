package com.collin.demo.api.version.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过参数控制版本
 *
 * @author collin
 * @date 2024-05-30
 */
@RestController
@RequestMapping("client/params")
public class ParamsController {

    @GetMapping(value = "test", params = "version=1")
    public ResponseEntity<String> testV1() {
        return ResponseEntity.ok("v1");
    }

    @GetMapping(value = "test", params = "version=2")
    public ResponseEntity<String> testV2() {
        return ResponseEntity.ok("v2");
    }

}