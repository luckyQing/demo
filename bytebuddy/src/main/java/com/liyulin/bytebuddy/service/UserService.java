package com.liyulin.bytebuddy.service;

import com.liyulin.bytebuddy.dto.RegisterReqDTO;
import com.liyulin.bytebuddy.dto.RegisterRespDTO;

public interface UserService {

    RegisterRespDTO register(RegisterReqDTO reqDTO);

}