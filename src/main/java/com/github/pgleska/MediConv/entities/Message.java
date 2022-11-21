package com.github.pgleska.MediConv.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "td", nullable = false)
	private LocalDateTime timestamp;
	
	@Column(name = "author_id", nullable = false)
	private Integer authorId;
	
	@Column(name = "receiver_id", nullable = false)
	private Integer receiverId;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "shared_key_by_author_pkey", nullable = false)
	private String sharedKeyEncryptedWithAuthorPKey;
	
	@Column(name = "shared_key_by_receiver_pkey", nullable = false)
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
	
}
