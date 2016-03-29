package com.lenicliu.spring.security;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableOAuth2Sso
@SpringBootApplication
@RestController
public class Application extends WebSecurityConfigurerAdapter {

	@RequestMapping("/date")
	public Object date() {
		return Collections.singletonMap("datetime", new Date());
	}

	@RequestMapping("/")
	public Object index() {
		return Collections.singletonMap("index", "ok");
	}

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests()//
				.antMatchers("/", "/login", "/webjars/**").permitAll()//
				.antMatchers("/date","/user").authenticated()//
				.and().logout().logoutSuccessUrl("/").permitAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}