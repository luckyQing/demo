package com.liyulin.hibernate.validator.controller;

import com.liyulin.hibernate.validator.entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final ResponseBodyAdviceController responseBodyAdviceController;

    @GetMapping
    public Result test() {
        return responseBodyAdviceController.test();
    }

}
