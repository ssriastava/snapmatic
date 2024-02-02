package com.snapmatic.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.snapmatic.auth.dto.LoginDTO;
import com.snapmatic.auth.dto.ResponseDTO;
import com.snapmatic.auth.dto.SignUpDTO;
import com.snapmatic.auth.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authservice;
	
	Logger log=LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/signup")
	public ResponseDTO signUp(@RequestBody SignUpDTO signup) {
		return authservice.signup(signup);
	}
	
	@PostMapping("/login")
	public ResponseDTO login(@RequestBody LoginDTO login, HttpServletResponse response) {
		log.debug("login request. "+login.getUsername());
		ResponseDTO resp=authservice.login(login);
		Cookie cookie=new Cookie("Session-id",resp.getMessage());
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60*15);
		response.addCookie(cookie);
		return resp;
	}
	
	@PostMapping("/refreshtoken")
	public ResponseDTO refresh(HttpServletRequest request, HttpServletResponse response) {
		if(WebUtils.getCookie(request, "Session-id")!=null)
		{
			ResponseDTO resp=authservice.refreshToken(WebUtils.getCookie(request, "Session-id").getValue());
			Cookie cookie=new Cookie("Session-id",resp.getMessage());
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60*15);
			response.addCookie(cookie);
			return resp;
		}
		else {
			ResponseDTO resp=new ResponseDTO(-1, "Logged off", false);
			return resp;
		}
	}
	
	

}
