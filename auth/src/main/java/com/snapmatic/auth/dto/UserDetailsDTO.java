package com.snapmatic.auth.dto;

public class UserDetailsDTO {

    String token;
    String userid;
    boolean success;

    public UserDetailsDTO(String token, String userid, boolean success) {
        this.token = token;
        this.userid = userid;
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
