package com.github.pgleska.MediConv.dtos;

import java.time.LocalDateTime;

import com.github.pgleska.MediConv.entities.Message;

public class MessageDTO {
	private Integer id;
	private LocalDateTime timestamp;
	private Integer authorId;
	private Integer receiverId;
	private String authorName;
	private String receiverName;
	private String content;
	private String sharedKeyEncryptedWithAuthorPKey;
	private String sharedKeyEncryptedWithReceiverPKey;

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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
	
	public String getSharedKeyEncryptedWithAuthorPKey() {
		return sharedKeyEncryptedWithAuthorPKey;
	}
	
	public void setSharedKeyEncryptedWithAuthorPKey(String sharedKeyEncryptedWithAuthorPKey) {
		this.sharedKeyEncryptedWithAuthorPKey = sharedKeyEncryptedWithAuthorPKey;
	}
	
	public String getSharedKeyEncryptedWithReceiverPKey() {
		return sharedKeyEncryptedWithReceiverPKey;
	}
	
	public void setSharedKeyEncryptedWithReceiverPKey(String sharedKeyEncryptedWithReceiverPKey) {
		this.sharedKeyEncryptedWithReceiverPKey = sharedKeyEncryptedWithReceiverPKey;
	}

	public static MessageDTO convertToDTO(Message message) {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setId(message.getId());
		messageDTO.setAuthorId(message.getAuthorId());
		messageDTO.setReceiverId(message.getReceiverId());
		messageDTO.setContent(message.getContent());
		messageDTO.setSharedKeyEncryptedWithAuthorPKey(message.getSharedKeyEncryptedWithAuthorPKey());
		messageDTO.setSharedKeyEncryptedWithReceiverPKey(message.getSharedKeyEncryptedWithReceiverPKey());
		messageDTO.setTimestamp(message.getTimestamp());
		return messageDTO;
	}
	
	public static Message convertToEntity(MessageDTO messageDTO) {
		Message message = new Message();
		message.setId(messageDTO.getId());
		message.setAuthorId(messageDTO.getAuthorId());
		message.setReceiverId(messageDTO.getReceiverId());
		message.setContent(messageDTO.getContent());
		message.setSharedKeyEncryptedWithAuthorPKey(messageDTO.getSharedKeyEncryptedWithAuthorPKey());
		message.setSharedKeyEncryptedWithReceiverPKey(messageDTO.getSharedKeyEncryptedWithReceiverPKey());
		message.setTimestamp(LocalDateTime.now());
		return message;
	}
}
