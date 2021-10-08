package com.example.demo.customauth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
	
	private AuthenticationManager authenticationManager;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		logger.info("#####################################filter construction####################");
		this.authenticationManager = authenticationManager;
		this.authenticationManager = authenticationManager;

//        setFilterProcessesUrl("/custom"); 
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		logger.info("#####################################authentication attempt####################");
		if(request.getHeader("Authorization") == null) {
			throw new CustomAuthenticationException("not authorized");
		}else {
			String credentials = request.getHeader("Authorization").split(" ")[1];
			String name = credentials.split(":")[0];
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new CustomAuthority("ROLE_USER"));
			authorities.add(new CustomAuthority("ROLE_CUSTOM"));
			return authenticationManager.authenticate(
					new CustomAuthentication(
							name, 
							authorities, 
							credentials)
					);
		}
	}
	
	

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		logger.info("##################################### successful auth ####################" + authResult);
//		super.successfulAuthentication(request, response, chain, authResult);
		 SecurityContextHolder.getContext().setAuthentication(authResult); // sets valid auth in security context
		chain.doFilter(request, response); // to kepp going or continue
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("##################################### do internals ####################");
		super.doFilter(request, response, chain);
//		chain.doFilter(request, response);
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		logger.info("##################################### requires auth ####################");
//		return super.requiresAuthentication(request, response);
		return true;
	}
	
	
	
	
	
}
