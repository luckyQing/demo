package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmartLifecycle3 implements SmartLifecycle {
    @Override
    public void start() {
        log.error("----> SmartLifecycle3#start");
        PrintUtil.add("SmartLifecycle3#start");
    }

    @Override
    public void stop() {
        log.error("----> SmartLifecycle3#stop");
        PrintUtil.add("SmartLifecycle3#stop");
    }

    @Override
    public boolean isRunning() {
        log.error("----> SmartLifecycle3#isRunning");
        PrintUtil.add("SmartLifecycle3#isRunning");
        return true;
    }

    @Override
    public int getPhase() {
        log.error("----> SmartLifecycle3#getPhase");
        PrintUtil.add("SmartLifecycle3#getPhase");
        return 3;
    }

}