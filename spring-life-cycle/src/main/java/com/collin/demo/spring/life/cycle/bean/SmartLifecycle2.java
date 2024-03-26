package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmartLifecycle2 implements SmartLifecycle {
    @Override
    public void start() {
        log.error("----> SmartLifecycle2#start");
        PrintUtil.add("SmartLifecycle2#start");
    }

    @Override
    public void stop() {
        log.error("----> SmartLifecycle2#stop");
        PrintUtil.add("SmartLifecycle2#stop");
    }

    @Override
    public boolean isRunning() {
        log.error("----> SmartLifecycle2#isRunning");
        PrintUtil.add("SmartLifecycle2#isRunning");
        return false;
    }

    @Override
    public int getPhase() {
        log.error("----> SmartLifecycle2#getPhase");
        PrintUtil.add("SmartLifecycle2#getPhase");
        return 2;
    }
    
}