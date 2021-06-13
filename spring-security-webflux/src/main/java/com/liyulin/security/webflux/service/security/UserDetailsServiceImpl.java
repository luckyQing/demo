package com.liyulin.security.webflux.service.security;

import com.liyulin.security.webflux.entity.Role;
import com.liyulin.security.webflux.entity.User;
import com.liyulin.security.webflux.pojo.bo.SecurityUserDetails;
import com.liyulin.security.webflux.service.PermissionService;
import com.liyulin.security.webflux.service.RoleService;
import com.liyulin.security.webflux.service.UserService;
import com.liyulin.security.webflux.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author collin
 * @date 2021-06-11
 */
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public Mono<UserDetails> findByUsername(String mobile) {
        if (mobile == null || mobile.trim().length() == 0) {
            return Mono.empty();
        }

        User user = userService.find(Long.parseLong(mobile));
        if (user == null) {
            throw new UsernameNotFoundException("The user is not found");
        }

        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        // 角色
        List<Integer> roleIds = new ArrayList<>();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(0);
            roleIds.add(role.getId());

            grantedAuthorities.add(addRole(role.getRole()));
        }

        // 权限
        Set<String> permissionSet = permissionService.findByRoleId(roleIds);
        String[] permissions = new String[permissionSet.size()];
        permissionSet.toArray(permissions);

        grantedAuthorities.addAll(AuthorityUtils.createAuthorityList(permissions));

        SecurityUserDetails securityUserDetails = new SecurityUserDetails(String.valueOf(user.getMobile()), user.getPassword(), grantedAuthorities);
        securityUserDetails.setRealName(user.getName());
        securityUserDetails.setToken(SecurityUtil.generateToken());
        return Mono.just(securityUserDetails);
    }

    private SimpleGrantedAuthority addRole(String role) {
        Assert.isTrue(!role.startsWith("ROLE_"), () -> role
                + " cannot start with ROLE_ (it is automatically added)");
        return new SimpleGrantedAuthority("ROLE_" + role);
    }

}