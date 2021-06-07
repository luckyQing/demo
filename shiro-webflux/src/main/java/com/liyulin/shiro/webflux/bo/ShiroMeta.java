package com.liyulin.shiro.webflux.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shiro.authz.annotation.Logical;

import java.io.Serializable;

/**
 * @author collin
 * @date 2021-06-07
 */
@Getter
@Setter
@ToString
public class ShiroMeta implements Serializable {

    private String url;
    private String httpMethod;

    private String[] permissions;
    private Logical permissionLogical;

    private String[] roles;
    private Logical roleLogical;

    private Boolean requiresUser;
    private Boolean requiresGuest;
    private Boolean requiresAuthentication;

}