package com.liyulin.shiro.service;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    Set<String> findByRoleId(List<Integer> roleIds);

}
