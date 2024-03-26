package com.collin.demo.spring.life.cycle.test;

import com.collin.demo.spring.life.cycle.App;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
public class AppTest {

    @Test
    public void test() {

    }

}