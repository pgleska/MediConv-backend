package com.github.pgleska.MediConv.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pgleska.MediConv.dtos.CredentialsDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
        	CredentialsDTO creds = new ObjectMapper()
                    .readValue(request.getInputStream(), CredentialsDTO.class);
            return authenticationManager.authenticate(
            		(Authentication) new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), Collections.emptyList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		
		String[] roles = new String[user.getAuthorities().size()];
		int i = 0;
		
		Iterator<GrantedAuthority> iter = user.getAuthorities().iterator();
		
		while(iter.hasNext()) {
			roles[i] = iter.next().getAuthority();
			i++;
		}
					
		String token = JWT.create()
				.withSubject(user.getUsername())
				.withArrayClaim("roles", roles)
				.sign(HMAC512(SECURITY_CONSTANTS.JWT_SECRET));
		
		String body = "{\"token\" : \""+ token + "\"}";
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(body);
        response.getWriter().flush();
        response.getWriter().close();
	}
	
	

}
