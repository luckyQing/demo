package com.liyulin.shiro.webflux.configure;

import com.liyulin.shiro.webflux.enums.ServerResponseEnum;
import com.liyulin.shiro.webflux.vo.ServerResponseVO;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ServerResponseVO UnAuthorizedExceptionHandler(UnauthorizedException e) {
        return ServerResponseVO.error(ServerResponseEnum.UNAUTHORIZED);
    }

}