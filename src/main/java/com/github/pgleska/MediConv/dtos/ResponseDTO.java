package com.github.pgleska.MediConv.dtos;

import org.springframework.http.HttpStatus;

public class ResponseDTO<T> {
	private Integer code;
	private String message;
	private T payload;
	
	public ResponseDTO(Integer code, String message, T payload) {
		this.code = code;
		this.message = message;
		this.payload = payload;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getPayload() {
		return payload;
	}
	
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
	public static <T> ResponseDTO<T> generateSuccessBody(T payload) {
		return new ResponseDTO<T>(HttpStatus.OK.value(), "success", payload);
	}
	
	public static <T> ResponseDTO<T> generateSuccessBody() {
		return new ResponseDTO<T>(HttpStatus.NO_CONTENT.value(), "success", null);
	}
	
	public static <T> ResponseDTO<T> generateCreatedBody(T payload) {
		return new ResponseDTO<T>(HttpStatus.CREATED.value(), "success", payload);
	}
	
	public static <T> ResponseDTO<T> generateErrorBody(HttpStatus httpStatus, String message) {
		return new ResponseDTO<T>(httpStatus.value(), message, null);
	}
}
