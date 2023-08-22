package com.liyulin.demo.openfeign.controller;

import com.liyulin.demo.openfeign.feign.IFeignTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController implements IFeignTest {

    @Override
    public ResponseEntity<String> query() {
        return ResponseEntity.ok("123");
    }

}