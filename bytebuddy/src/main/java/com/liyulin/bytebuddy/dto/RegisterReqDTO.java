package com.liyulin.bytebuddy.dto;

import lombok.Data;

@Data
public class RegisterReqDTO {

    private String username;
    private String password;
    private int testTag;

}