package com.liyulin.demo.openfeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "feigntest")
public interface IFeignTest {

    @GetMapping("demo/feign/test/query")
    ResponseEntity<String> query();

}