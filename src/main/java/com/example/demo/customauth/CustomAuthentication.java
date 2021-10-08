package com.example.demo.customauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthentication implements Authentication{
	private Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Collection<GrantedAuthority> authorities;
	private String credentials;
	private boolean authenticated;
	private List<String> roles = new ArrayList();
	
	
	/**
	 * 
	 * @param name
	 * @param authorities
	 * @param credentials
	 * @param authenticated
	 */
	public CustomAuthentication(
			String name, 
			Collection<GrantedAuthority> authorities, 
			String credentials
		) {
		this.name = name;
		this.authorities = authorities;
		this.credentials = credentials;
//		roles.add("USE pR");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		logger.info("###################  get authorities ############################");
		return this.authorities;
	}

	@Override
	public String getCredentials() {
		// TODO Auto-generated method stub
		return this.credentials;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.authenticated = isAuthenticated;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + credentials + authenticated +authorities.size();
	}
}
