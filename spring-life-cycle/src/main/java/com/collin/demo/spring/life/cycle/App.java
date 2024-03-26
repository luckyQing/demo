package com.collin.demo.spring.life.cycle;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        PrintUtil.print();
    }

}