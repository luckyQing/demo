package com.liyulin.security.webflux.service.security;

import com.liyulin.security.webflux.entity.Role;
import com.liyulin.security.webflux.service.PermissionService;
import com.liyulin.security.webflux.service.RoleService;
import com.liyulin.security.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author collin
 * @date 2021-06-11
 */
@Service
public class GatewayUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        com.liyulin.security.webflux.entity.User user = userService.findByAccount(username);
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        // 角色
        String[] roles = new String[roleList.size()];
        List<Integer> roleIds = new ArrayList<>();
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(0);
            roles[i] = role.getRole();
            roleIds.add(role.getId());
        }

        // 权限
        Set<String> permissionSet = permissionService.findByRoleId(roleIds);
        String[] permissions = new String[permissionSet.size()];
        permissionSet.toArray(permissions);

        UserDetails userDetails = User.withUsername(user.getUsername())
                .roles(roles)
                .authorities(permissions)
                .build();
        return Mono.just(userDetails);
    }

}