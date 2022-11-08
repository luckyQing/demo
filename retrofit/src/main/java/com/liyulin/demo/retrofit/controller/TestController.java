package com.liyulin.demo.retrofit.controller;

import io.github.smart.cloud.common.pojo.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping
    public Response<String> test(String name) {
        return new Response("ok:" + name);
    }

}