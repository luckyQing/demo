package com.liyulin.spring.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.liyulin.spring.security.pojo.bo.UserBO;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
		// 这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
		if (username.equals("admin")) {
			// 假设返回的用户信息如下;
			UserBO userInfo = new UserBO();
			userInfo.setUsername("admin");
			userInfo.setPassword("123456");
			
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("admin"));
			userInfo.setAuthorities(authorities);
			
			return userInfo;
		}

		return new UserBO();
	}

}