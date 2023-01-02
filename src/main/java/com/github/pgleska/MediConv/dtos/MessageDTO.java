package com.github.pgleska.MediConv.dtos;

import java.time.LocalDateTime;

import com.github.pgleska.MediConv.entities.Message;

public class MessageDTO {
	
	private Integer id;
	
	private LocalDateTime timestamp;
	
	private Integer authorId;
	
	private Integer receiverId;
	
	private String content;
	
	private String authorSecretKey;
	
	private String receiverSecretKey;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public Integer getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public Integer getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAuthorSecretKey() {
		return authorSecretKey;
	}
	
	public void setAuthorSecretKey(String sharedKeyEncryptedWithAuthorPKey) {
		this.authorSecretKey = sharedKeyEncryptedWithAuthorPKey;
	}
	
	public String getReceiverSecretKey() {
		return receiverSecretKey;
	}
	
	public void setReceiverSecretKey(String sharedKeyEncryptedWithReceiverPKey) {
		this.receiverSecretKey = sharedKeyEncryptedWithReceiverPKey;
	}

	public static MessageDTO convertToDTO(Message message) {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setId(message.getId());
		messageDTO.setAuthorId(message.getAuthorId());
		messageDTO.setReceiverId(message.getReceiverId());
		messageDTO.setContent(message.getContent());
		messageDTO.setAuthorSecretKey(message.getAuthorSecretKey());
		messageDTO.setReceiverSecretKey(message.getReceiverSecretKey());
		messageDTO.setTimestamp(message.getTimestamp());
		return messageDTO;
	}
	
	public static Message convertToEntity(MessageDTO messageDTO) {
		Message message = new Message();
		message.setId(messageDTO.getId());
		message.setAuthorId(messageDTO.getAuthorId());
		message.setReceiverId(messageDTO.getReceiverId());
		message.setContent(messageDTO.getContent());
		message.setAuthorSecretKey(messageDTO.getAuthorSecretKey());
		message.setReceiverSecretKey(messageDTO.getReceiverSecretKey());
		message.setTimestamp(LocalDateTime.now());
		return message;
	}
}
