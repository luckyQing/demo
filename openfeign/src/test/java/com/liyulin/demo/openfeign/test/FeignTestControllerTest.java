package com.liyulin.demo.openfeign.test;

import com.liyulin.demo.openfeign.Application;
import com.liyulin.demo.openfeign.feign.IFeignTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FeignTestControllerTest {

    @Autowired
    private IFeignTest feignTest;

    @Test
    void test() {
        ResponseEntity<String> response = feignTest.query();
        log.info("code={}, body={}", response.getStatusCode(), response.getBody());
    }

}