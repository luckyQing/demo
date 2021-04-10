package com.liyulin.shiro.service.impl;

import com.liyulin.shiro.entity.Role;
import com.liyulin.shiro.mapper.RoleMapper;
import com.liyulin.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleByUserId(Integer id) {
        return roleMapper.findRoleByUserId(id);
    }

}