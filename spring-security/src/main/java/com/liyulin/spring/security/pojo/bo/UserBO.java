package com.liyulin.spring.security.pojo.bo;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBO implements UserDetails {

	private Long id;
	private String username;
	private String password;
	private Set<SimpleGrantedAuthority> authorities;

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * 帐户是否过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 帐户是否被冻结
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 帐户密码是否过期，一般有的密码要求性高的系统会使用到，每隔一段时间就要求用户重置密码
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 帐号是否可用
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}