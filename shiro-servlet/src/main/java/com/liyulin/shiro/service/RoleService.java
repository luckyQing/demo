package com.liyulin.shiro.service;

import com.liyulin.shiro.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRoleByUserId(Integer id);

}