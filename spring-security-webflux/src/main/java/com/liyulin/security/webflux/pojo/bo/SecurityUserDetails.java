package com.liyulin.security.webflux.pojo.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author collin
 * @date 2021-06-15
 */
@Setter
@Getter
@ToString
public class SecurityUserDetails extends User implements Serializable {

    /**
     * token
     */
    private String token;
    /**
     * 姓名
     */
    private String realName;

    public SecurityUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}