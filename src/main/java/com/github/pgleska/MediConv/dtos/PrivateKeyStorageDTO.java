package com.github.pgleska.MediConv.dtos;

import com.github.pgleska.MediConv.entities.PrivateKeyStorage;

public class PrivateKeyStorageDTO {
	private Integer id;
	private Integer userId;
	private String privateKey;
	
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
	
	public static PrivateKeyStorageDTO convertToDTO(PrivateKeyStorage privateKeyStorage) {
		PrivateKeyStorageDTO dto = new PrivateKeyStorageDTO();
		dto.setId(privateKeyStorage.getId());
		dto.setUserId(privateKeyStorage.getUserId());
		dto.setPrivateKey(privateKeyStorage.getPrivateKey());
		return dto;
	}
	
	public static PrivateKeyStorage convertToEntity(PrivateKeyStorageDTO dto) {
		PrivateKeyStorage entity = new PrivateKeyStorage();
		entity.setUserId(dto.getUserId());
		entity.setPrivateKey(dto.getPrivateKey());
		return entity;
	}
}
