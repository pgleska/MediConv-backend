package com.github.pgleska.MediConv.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.github.pgleska.MediConv.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pgleska.MediConv.dtos.PrivateKeyStorageDTO;
import com.github.pgleska.MediConv.dtos.ResponseDTO;
import com.github.pgleska.MediConv.dtos.UserDTO;
import com.github.pgleska.MediConv.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;		
	}
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<UserDTO>> createAccount(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(ResponseDTO.generateErrorBody(HttpStatus.UNPROCESSABLE_ENTITY, "invalid.input"), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		UserDTO result = userService.registerNewUser(userDTO);
		return new ResponseEntity<>(ResponseDTO.generateCreatedBody(result), HttpStatus.CREATED);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<UserDTO>> getRequester(Principal principal) {
		return new ResponseEntity<>(ResponseDTO.generateSuccessBody(userService.getUser(principal.getName())), HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<ResponseDTO<List<UserDTO>>> searchForUser(@RequestParam(value = "seq", required = false) String sequence, Principal principal) {
		String requesterName = principal.getName();		
		List<UserDTO> users = userService.searchForUsers(requesterName, sequence);
		return new ResponseEntity<>(ResponseDTO.generateSuccessBody(users), HttpStatus.OK);
	}
	
	@PostMapping(value = "/publicKey", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<UserDTO>> setPublicKey(@RequestBody UserDTO userDTO, Principal principal) {		
		UserDTO result = userService.setPublicKey(userDTO, principal.getName());
		return new ResponseEntity<>(ResponseDTO.generateCreatedBody(result), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/privateKey", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<PrivateKeyStorageDTO>> getPrivateKey(Principal principal) {		
		PrivateKeyStorageDTO result = userService.getPrivateKey(principal.getName());
		return new ResponseEntity<>(ResponseDTO.generateCreatedBody(result), HttpStatus.OK);
	}
	
	@PostMapping(value = "/privateKey", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<PrivateKeyStorageDTO>> setPrivateKey(@RequestBody PrivateKeyStorageDTO dto, Principal principal) {		
		PrivateKeyStorageDTO result = userService.setPrivateKey(dto, principal.getName());
		return new ResponseEntity<>(ResponseDTO.generateCreatedBody(result), HttpStatus.CREATED);
	}
}
