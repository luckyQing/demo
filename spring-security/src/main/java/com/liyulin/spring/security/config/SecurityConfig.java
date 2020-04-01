package com.liyulin.spring.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider provider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(provider).httpBasic()
				// 未登录
				.authenticationEntryPoint((request, response, authException) -> {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					returnJSON(response, new LoginResultVO(403, "未登录"));
				})

				.and().authorizeRequests().anyRequest().authenticated() // 必须授权才能范围

				.and().formLogin() // 使用自带的登录
				.loginPage("/login").permitAll()
				// 登录失败
				.failureHandler((request, response, ex) -> {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					LoginResultVO loginResultVO = new LoginResultVO();
					loginResultVO.setCode(401);
					if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
						loginResultVO.setMsg("用户名或密码错误");
					} else if (ex instanceof DisabledException) {
						loginResultVO.setMsg("账户被禁用");
					} else {
						loginResultVO.setMsg("登录失败!");
					}

					returnJSON(response, loginResultVO);
				})
				// 登录成功
				.successHandler(
						(request, response, authentication) -> returnJSON(response, new LoginResultVO(200, "登录成功")))
				.and().exceptionHandling()
				// 没有权限，返回json
				.accessDeniedHandler((request, response, ex) -> {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					returnJSON(response, new LoginResultVO(403, "权限不足"));
				}).and().logout()
				// 退出成功
				.logoutSuccessHandler(
						(request, response, authentication) -> returnJSON(response, new LoginResultVO(200, "退出成功")))
				.permitAll();
		// 开启跨域访问
		http.cors().disable();
		// 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
		http.csrf().disable();

//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().formLogin().and().csrf().disable().authorizeRequests()
//		.antMatchers("/user/**").hasRole("USER")
//		.antMatchers("/css/**", "/index").permitAll()
//        .anyRequest().authenticated()
//		.and().formLogin().loginPage("/login")
//		.loginProcessingUrl("/login")
//		.defaultSuccessUrl("/index") // 成功登陆后跳转页面
//		.failureUrl("/loginError").permitAll();
	}

	private void returnJSON(HttpServletResponse response, Object result) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(result));
		out.flush();
		out.close();
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	private class LoginResultVO {
		private int code;
		private String msg;
	}

}