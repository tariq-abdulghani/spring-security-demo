package com.example.demo.com.example.demo.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
//@EnableWebSecurity
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/users/**")
			.authorizeRequests()
			.antMatchers("/**")
			.hasAnyRole("ADMIN", "USER").and().httpBasic().realmName("users-realm")
//		.and()
//			.authorizeRequests()
//			.antMatchers("/admins/**")
//			.hasAnyRole("ADMIN").and().httpBasic().realmName("adminsss")
//	.and()
//			.authorizeRequests()
//			.antMatchers("/", "/home", "/index.html")
//			.permitAll()
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
				.withUser("user")
				.password("{noop}user")
				.roles("USER")
			.and()
				.withUser("admin")
				.password("{noop}admin")
				.roles("USER", "ADMIN");
	}

	
	
}
