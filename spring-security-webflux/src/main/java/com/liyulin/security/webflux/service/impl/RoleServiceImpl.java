package com.liyulin.security.webflux.service.impl;

import com.liyulin.security.webflux.entity.Role;
import com.liyulin.security.webflux.mapper.RoleMapper;
import com.liyulin.security.webflux.service.RoleService;
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