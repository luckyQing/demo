package com.liyulin.security.webflux.service;

import com.liyulin.security.webflux.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRoleByUserId(Integer id);

}