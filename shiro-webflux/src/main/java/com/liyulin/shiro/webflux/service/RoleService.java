package com.liyulin.shiro.webflux.service;

import com.liyulin.shiro.webflux.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRoleByUserId(Integer id);

}