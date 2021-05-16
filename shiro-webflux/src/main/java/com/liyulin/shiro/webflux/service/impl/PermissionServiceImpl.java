package com.liyulin.shiro.webflux.service.impl;

import com.liyulin.shiro.webflux.mapper.PermissionMapper;
import com.liyulin.shiro.webflux.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> findByRoleId(List<Integer> roleIds) {
        return permissionMapper.findByRoleId(roleIds);
    }
}
