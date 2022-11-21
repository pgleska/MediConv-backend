package com.github.pgleska.MediConv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.github.pgleska.MediConv.services.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class APISecurityConfig {	

	@Autowired
	private final UserDetailsServiceImpl userDetailsService;
	
	public APISecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsService = userDetailsServiceImpl;
	}
	
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder())
				.and()
				.build();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		http.csrf().disable()			
			.authorizeHttpRequests()
			.antMatchers(HttpMethod.POST, "/login", "/api/user/register").permitAll()
			.antMatchers("/api/**").hasAnyAuthority("USER", "ADMIN", "DOCTOR")			
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(http, userDetailsService)))
			.addFilterBefore(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class)
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);		
		
		return http.build();
	}
}

