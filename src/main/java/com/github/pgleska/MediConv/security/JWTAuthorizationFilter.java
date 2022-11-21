package com.github.pgleska.MediConv.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		
		if(Objects.isNull(token) || !token.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request, token);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {		
		token = token.replace("Bearer ", "");
		
		DecodedJWT decodedJWT = JWT.require(HMAC512(SECURITY_CONSTANTS.JWT_SECRET))
				.build()
				.verify(token);

		String userLogin = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);						
		
		Collection<SimpleGrantedAuthority> authorities = roles.stream()
				.map(r -> new SimpleGrantedAuthority(r))
				.collect(Collectors.toList());
		
		return new UsernamePasswordAuthenticationToken(userLogin, null, authorities);
	}

}
