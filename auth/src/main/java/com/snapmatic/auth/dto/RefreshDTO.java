package com.snapmatic.auth.dto;

public class RefreshDTO {
	
	String token;
	
	public RefreshDTO() {
		
	}

	public RefreshDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
