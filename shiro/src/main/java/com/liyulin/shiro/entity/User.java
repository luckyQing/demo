package com.liyulin.shiro.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -6056125703075132981L;

    private Integer id;

    private String account;

    private String password;

    private String username;
}