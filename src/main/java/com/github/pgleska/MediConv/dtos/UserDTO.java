package com.github.pgleska.MediConv.dtos;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.pgleska.MediConv.entities.Role;
import com.github.pgleska.MediConv.entities.User;

public class UserDTO {
	private Integer id;
	private String email;
	private String password;
	
	@NotBlank
	private String name;
	
	private Role role;
	
	private String publicKey;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	public static UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setName(user.getName());
		userDTO.setRole(user.getRole());
		userDTO.setPublicKey(user.getPublicKey());
		return userDTO;
	}
	
	public static User convertToEntity(UserDTO userDTO, PasswordEncoder encoder) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setName(userDTO.getName());
		user.setRole(userDTO.getRole());
		return user;
	}
}
