package com.example.demo.com.example.demo.securityconfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	 @Override
//	    public void configure(WebSecurity web) throws Exception {
//	        web
//	           .ignoring()
//	           .antMatchers("/h2-console/**");
//	    }
	 
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.httpBasic()
	    .and()
	    	.authorizeRequests()
		    .antMatchers("/index.html", "/", "/home", "/login", "/h2-console/**")
		    .permitAll()
		    .anyRequest()
		    .authenticated()
	    .and()
        	.csrf()
        	.ignoringAntMatchers("/h2-console/**")
        .and().headers()
        	.frameOptions()
        	.disable()
        .and()
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	  public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder
	    .inMemoryAuthentication()
		    .withUser("user")
		    .password("{noop}password")
		    .roles("USER")
	    .and()
	    	.withUser("admin")
	    	.password("{noop}admin")
	    	.roles("USER", "ADMIN");
	  }

	
//	@Bean
//	public static NoOpPasswordEncoder passwordEncoder() {
//	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
	
}
