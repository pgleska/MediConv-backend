package com.github.pgleska.MediConv.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.pgleska.MediConv.daos.UserDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserDAO userDAO;
	
	public UserDetailsServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.github.pgleska.MediConv.entities.User user = userDAO.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("user.not.found"));
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return new User(user.getEmail(), user.getPassword(), authorities);
	}

}
