package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        log.error("----> DisposableBean");
        PrintUtil.add("DisposableBean");
    }

}