package com.liyulin.demo.logback;

import com.liyulin.demo.logback.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LogbackTest {

    @Test
    public void shouldAnswerWithTrue() {
        ProductDTO product = new ProductDTO();
        product.setId(100L);
        product.setName("mobile xx");

        log.info("{}", product);
    }

}