package com.liyulin.bytebuddy;

import com.liyulin.bytebuddy.advice.RegisterAdvice;
import com.liyulin.bytebuddy.advice.RegisterInterceptor;
import com.liyulin.bytebuddy.dto.RegisterReqDTO;
import com.liyulin.bytebuddy.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

@Slf4j
public class BytebuddyTest {

    @Test
    public void testAdvice() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(UserServiceImpl.class)
                .visit(Advice.to(RegisterAdvice.class).on(ElementMatchers.named("register")))
                .make()
                .load(UserServiceImpl.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        RegisterReqDTO reqDTO = new RegisterReqDTO();
        reqDTO.setUsername("admin");
        reqDTO.setPassword("123456");

        UserServiceImpl userService = new UserServiceImpl();
        log.info("return---->{}", userService.register(reqDTO));
    }

    @Test
    public void testInterceptor() {
        ByteBuddyAgent.install();
        new AgentBuilder.Default()
                // 不能通过类获取类名；否则会导致类被提前加载，从而无法进行字节码增强
                .type(ElementMatchers.named("com.liyulin.bytebuddy.service.impl.UserServiceImpl"))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(ElementMatchers.named("register"))
                                .intercept(MethodDelegation.to(RegisterInterceptor.class)))
                .installOnByteBuddyAgent();

        RegisterReqDTO reqDTO = new RegisterReqDTO();
        reqDTO.setUsername("root");
        reqDTO.setPassword("000000");
        reqDTO.setTestTag(1);

        UserServiceImpl userService = new UserServiceImpl();
        log.info("return---->{}", userService.register(reqDTO));
    }

}