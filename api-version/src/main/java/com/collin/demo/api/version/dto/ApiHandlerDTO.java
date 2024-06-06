package com.collin.demo.api.version.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
@AllArgsConstructor
public class ApiHandlerDTO {

    private Object handler;
    private Method handlerMethod;

}