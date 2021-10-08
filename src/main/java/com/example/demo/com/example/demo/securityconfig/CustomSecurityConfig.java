package com.example.demo.com.example.demo.securityconfig;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.demo.customauth.CustomAuthenticationFilter;
import com.example.demo.customauth.CustomAuthenticationProvider;


@Order(SecurityProperties.BASIC_AUTH_ORDER - 12)
@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/custom/**")
		.addFilterBefore(new CustomAuthenticationFilter(authenticationManager()),BasicAuthenticationFilter.class)
		.authorizeRequests().anyRequest().hasAnyRole( "CUSTOM");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(new CustomAuthenticationProvider());
	}
}
