package com.snapmatic.auth.dto;

public class RefreshTokenDTO {

    String token;

    public RefreshTokenDTO() {super();}

    public RefreshTokenDTO(String token) {
        super();
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
