package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootRestSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestSecurityDemoApplication.class, args);
	}
	
	
	@GetMapping(path = "/")
	public String getIndex(){
		return "Hello security";
	}
	
	@GetMapping(path = "/users")
	public String getUsers(){
		return "Hello users";
	}
	
	@GetMapping(path = "/admins")
	public String getAdmin(){
		return "Hello admin";
	}
}
