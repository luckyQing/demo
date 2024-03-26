package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmartLifecycle1 implements SmartLifecycle {
    @Override
    public void start() {
        log.error("----> SmartLifecycle1#start");
        PrintUtil.add("SmartLifecycle1#start");
    }

    @Override
    public void stop() {
        log.error("----> SmartLifecycle1#stop");
        PrintUtil.add("SmartLifecycle1#stop");
    }

    @Override
    public boolean isRunning() {
        log.error("----> SmartLifecycle1#isRunning");
        PrintUtil.add("SmartLifecycle1#isRunning");
        return false;
    }

    @Override
    public int getPhase() {
        log.error("----> SmartLifecycle1#getPhase");
        PrintUtil.add("SmartLifecycle1#getPhase");
        return 1;
    }

}