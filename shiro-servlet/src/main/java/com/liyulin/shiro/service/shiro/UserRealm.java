package com.liyulin.shiro.service.shiro;

import com.liyulin.shiro.entity.Role;
import com.liyulin.shiro.entity.User;
import com.liyulin.shiro.service.PermissionService;
import com.liyulin.shiro.service.RoleService;
import com.liyulin.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 负责认证用户身份和对用户进行授权
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 用户授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer uid = (Integer) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = roleService.findRoleByUserId(uid);
        Set<String> roleSet = new HashSet<>();
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleSet.add(role.getRole());
            roleIds.add(role.getId());
        }
        // 放入角色信息
        authorizationInfo.setRoles(roleSet);
        // 放入权限信息
        authorizationInfo.setStringPermissions(permissionService.findByRoleId(roleIds));

        return authorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        User user = userService.findByAccount(token.getUsername());
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
    }

}