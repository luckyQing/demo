package com.liyulin.hibernate.validator.controller;

import com.liyulin.hibernate.validator.annotation.LightningResponseBody;
import com.liyulin.hibernate.validator.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("responseBodyAdvice")
public class ResponseBodyAdviceController {

    @GetMapping
    @LightningResponseBody
    public Result test() {
        return new Result();
    }

}
