package com.liyulin.shiro.service;

import java.util.List;

public interface PermissionService {

    List<String> findByRoleId(List<Integer> roleIds);

}
