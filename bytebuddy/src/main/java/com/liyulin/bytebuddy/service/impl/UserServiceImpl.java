package com.liyulin.bytebuddy.service.impl;

import com.liyulin.bytebuddy.dto.RegisterReqDTO;
import com.liyulin.bytebuddy.dto.RegisterRespDTO;
import com.liyulin.bytebuddy.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {

    private String version = "v1.0.1";

    @Override
    public RegisterRespDTO register(RegisterReqDTO reqDTO) {
        log.info("register.req={}", reqDTO);

        RegisterRespDTO respDTO = new RegisterRespDTO();
        respDTO.setId(100L);
        respDTO.setRole("admin");
        return respDTO;
    }

}