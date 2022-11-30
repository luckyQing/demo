package com.liyulin.demo.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MaskMessageConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return "mm==>"+event.getFormattedMessage();
    }

}