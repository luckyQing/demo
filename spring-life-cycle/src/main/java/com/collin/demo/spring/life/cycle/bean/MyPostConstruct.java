package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MyPostConstruct {

    @PostConstruct
    public void init() {
        log.error("----> PostConstruct");
        PrintUtil.add("PostConstruct");
    }

}