package com.example.demo.customauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	private Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.info("###################   creds %s ############################");
		if(authentication.getCredentials().toString().equalsIgnoreCase("user:user") ){
			authentication.setAuthenticated(true);
			return authentication;
		}
//		return null;
		throw new CustomAuthenticationException("not authorized");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		logger.info("################ supports ###################### "+ authentication.getName().equalsIgnoreCase(CustomAuthentication.class.getName()));
		return authentication.getName().equalsIgnoreCase(CustomAuthentication.class.getName());
	}

}
