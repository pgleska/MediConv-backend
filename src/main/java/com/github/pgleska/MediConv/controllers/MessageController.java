package com.github.pgleska.MediConv.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pgleska.MediConv.dtos.MessageDTO;
import com.github.pgleska.MediConv.dtos.ResponseDTO;
import com.github.pgleska.MediConv.services.MessageService;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<?>> sendMessage(@RequestBody MessageDTO messageDTO) {
		MessageDTO m = messageService.saveMessage(messageDTO);
		return new ResponseEntity<>(ResponseDTO.generateCreatedBody(m), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<List<MessageDTO>>> downloadMessages(Principal principal, @PathVariable("id") Integer otherUserId) {
		String requesterEmail = principal.getName();
		List<MessageDTO> messages = messageService.downloadMessages(requesterEmail, otherUserId);
		return new ResponseEntity<>(ResponseDTO.generateSuccessBody(messages), HttpStatus.OK);
	}		
}
