package com.github.pgleska.MediConv.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pgleska.MediConv.daos.PrivateKeyStorageDAO;
import com.github.pgleska.MediConv.daos.UserDAO;
import com.github.pgleska.MediConv.dtos.PrivateKeyStorageDTO;
import com.github.pgleska.MediConv.dtos.UserDTO;
import com.github.pgleska.MediConv.entities.Role;
import com.github.pgleska.MediConv.entities.User;

@Service
@Transactional
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserDAO userDAO;
	private final PrivateKeyStorageDAO privateKeyStorageDAO;
	
	public UserService(UserDAO userDAO, PrivateKeyStorageDAO privateKeyStorageDAO, PasswordEncoder passwordEncoder) {		
		this.userDAO = userDAO;
		this.passwordEncoder = passwordEncoder;
		this.privateKeyStorageDAO = privateKeyStorageDAO;
	}
	
	public UserDTO registerNewUser(UserDTO userDTO) {
		User user = userDAO.save(UserDTO.convertToEntity(userDTO, passwordEncoder));
		return UserDTO.convertToDTO(user);
	}

	public UserDTO getUser(String email) {
		return UserDTO.convertToDTO(userDAO.findByEmail(email).get());
	}

	public List<UserDTO> searchForUsers(String requesterName, String seq) {
		User user = userDAO.findByName(requesterName).get();
		Role role = user.getRole();
		List<UserDTO> users = new ArrayList<>();
		
		if(role.equals(Role.USER)) {
			users = userDAO.findByNameContainingAndRole(seq, Role.DOCTOR).stream().map(u -> UserDTO.convertToDTO(u)).collect(Collectors.toList());
		} else if(role.equals(Role.DOCTOR)) {
			users = userDAO.findByNameContainingAndRole(seq, Role.USER).stream().map(u -> UserDTO.convertToDTO(u)).collect(Collectors.toList());
		}
		
		users.removeIf(u -> u.getId() == user.getId());
		return users;
	}
	
	public UserDTO setPublicKey(UserDTO userDTO, String requesterEmail) {
		User user = userDAO.findByEmail(requesterEmail).get();
		user.setPublicKey(userDTO.getPublicKey());
		return UserDTO.convertToDTO(user);
	}
	
	public PrivateKeyStorageDTO getPrivateKey(String requesterEmail) {
		User user = userDAO.findByEmail(requesterEmail).get();
		return PrivateKeyStorageDTO.convertToDTO(privateKeyStorageDAO.findByUserId(user.getId()).get());		
	}
	
	public PrivateKeyStorageDTO setPrivateKey(PrivateKeyStorageDTO dto, String requesterEmail) {
		User user = userDAO.findByEmail(requesterEmail).get();
		dto.setUserId(user.getId());
		return PrivateKeyStorageDTO.convertToDTO(privateKeyStorageDAO.save(PrivateKeyStorageDTO.convertToEntity(dto)));		
	}
}
