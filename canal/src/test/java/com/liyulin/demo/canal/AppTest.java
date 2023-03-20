package com.liyulin.demo.canal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppTest {

    @Test
    void test() throws InterruptedException {
        TimeUnit.MINUTES.sleep(10);
    }

}