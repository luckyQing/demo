package com.liyulin.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/user/**").hasRole("USER")
//		.and().formLogin().and().csrf().disable().authorizeRequests() // 关闭CSRF
//		.antMatchers("/css/**", "/index").permitAll()
//        .anyRequest().authenticated()
//		.and().formLogin().loginPage("/login")
//		.loginProcessingUrl("/login")
//		.defaultSuccessUrl("/index") // 成功登陆后跳转页面
//		.failureUrl("/loginError").permitAll();
		
		
		http
        .formLogin()
            .and().csrf().disable() //关闭CSRF
            .formLogin().loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/index") //成功登陆后跳转页面
            .failureUrl("/login/error").permitAll()
            .and()
        .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()   
                .antMatchers("/user/**").hasRole("USER")  
                .anyRequest().authenticated();
		
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

}