package com.liyulin.shiro.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Role implements Serializable {

    private static final long serialVersionUID = -1767327914553823741L;

    private Integer id;

    private String role;

    private String desc;

}