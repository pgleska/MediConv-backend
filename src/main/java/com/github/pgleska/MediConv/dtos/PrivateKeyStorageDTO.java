package com.github.pgleska.MediConv.dtos;

import com.github.pgleska.MediConv.entities.PrivateKeyStorage;

public class PrivateKeyStorageDTO {
	private Integer id;
	private Integer userId;
	private String privateKey;
	private String salt;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public static PrivateKeyStorageDTO convertToDTO(PrivateKeyStorage entity) {
		PrivateKeyStorageDTO dto = new PrivateKeyStorageDTO();
		dto.setId(entity.getId());
		dto.setUserId(entity.getUserId());
		dto.setPrivateKey(entity.getPrivateKey());
		dto.setSalt(entity.getSalt());
		return dto;
	}
	
	public static PrivateKeyStorage convertToEntity(PrivateKeyStorageDTO dto) {
		PrivateKeyStorage entity = new PrivateKeyStorage();
		entity.setUserId(dto.getUserId());
		entity.setPrivateKey(dto.getPrivateKey());
		entity.setSalt(dto.getSalt());
		return entity;
	}
}
