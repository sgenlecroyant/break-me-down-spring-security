package com.sgen.breakmedown.breakmedown.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.headers().frameOptions().disable()
			.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/**").permitAll();
	}
	
	
}
