package com.snapmatic.auth.dto;

public class ResponseDTO {
	
	int status;
	String message;
	boolean success;
	
	public ResponseDTO(int status, String message, boolean success) {
		super();
		this.status = status;
		this.message = message;
		this.success=success;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
