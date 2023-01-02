package com.github.pgleska.MediConv.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pgleska.MediConv.daos.MessageDAO;
import com.github.pgleska.MediConv.daos.UserDAO;
import com.github.pgleska.MediConv.dtos.MessageDTO;
import com.github.pgleska.MediConv.entities.User;

@Service
@Transactional
public class MessageService {
	
	private final MessageDAO messageDAO;
	private final UserDAO userDAO;
	
	public MessageService(MessageDAO messageDAO, UserDAO userDAO) {
		this.messageDAO = messageDAO;
		this.userDAO = userDAO;
	}
	
	public MessageDTO saveMessage(MessageDTO messageDTO, String requesterEmail) {
		User user = userDAO.findByEmail(requesterEmail).get();
		messageDTO.setAuthorId(user.getId());
		return MessageDTO.convertToDTO(messageDAO.save(MessageDTO.convertToEntity(messageDTO)));
	}
	
	public List<MessageDTO> downloadMessages(String requesterEmail, Integer otherUserId) {
		User user = userDAO.findByEmail(requesterEmail).orElse(null);
		User otherUser = userDAO.findById(otherUserId).orElse(null);
		List<Integer> authorsId = List.of(user.getId(), otherUser.getId());
		List<Integer> receiversId = List.of(user.getId(), otherUser.getId());				
		
		return messageDAO.findAllByAuthorIdInAndReceiverIdInOrderByTimestampDesc(authorsId, receiversId)
				.stream()
				.map(m -> MessageDTO.convertToDTO(m))
				.peek(m -> {
					if(m.getAuthorId() == user.getId()) {
						m.setAuthorName(user.getName());
						m.setReceiverName(otherUser.getName());
					} else {
						m.setAuthorName(otherUser.getName());
						m.setReceiverName(user.getName());
					}
				})
				.collect(Collectors.toList());
	}
}
