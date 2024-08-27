package com.liyulin.bytebuddy.advice;

import com.liyulin.bytebuddy.dto.RegisterReqDTO;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class RegisterInterceptor {

    public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegisterInterceptor.class);

    @RuntimeType
    public static Object intercept(@Argument(0) RegisterReqDTO reqDTO, @SuperCall Callable<?> callable) throws Exception {
        log.info("intercept start---->{}", reqDTO);
        Object result = callable.call();
        log.info("intercept end---->{}", result);
        return result;
    }

}