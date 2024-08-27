package com.liyulin.bytebuddy.advice;

import com.liyulin.bytebuddy.dto.RegisterReqDTO;
import com.liyulin.bytebuddy.dto.RegisterRespDTO;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RegisterAdvice {

    public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegisterAdvice.class);

    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method, @Advice.Argument(0) RegisterReqDTO reqDTO,
                                     @Advice.FieldValue("version") String version) {
        log.info("Enter [{}][{}] with arguments: {}", version, method.getName(), reqDTO);
        if (reqDTO != null && reqDTO.getUsername() != null) {
            reqDTO.setUsername(reqDTO.getUsername() + "_updated");
            log.info("Enter [{}] with updated arguments: {}", method.getName(), reqDTO);
        }
    }

    @Advice.OnMethodExit(suppress = Throwable.class, onThrowable = Throwable.class)
    public static void onMethodExit(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments,
                                    @Advice.Thrown Throwable throwable, @Advice.Return(readOnly = false) RegisterRespDTO ret) {
        if (throwable != null) {
            log.error("---->error", throwable);
        }
        log.info("Exit [{}] with arguments: {} return: {}", method.getName(), Arrays.toString(arguments), ret);
        ret.setRole(ret.getRole() + "_updated");
    }

}