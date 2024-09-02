package com.snapmatic.auth.controller;

import com.snapmatic.auth.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import com.snapmatic.auth.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

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
	public UserDetailsDTO login(@RequestBody LoginDTO login, HttpServletResponse response) {
		log.debug("login request. "+login.getUsername());
		return authservice.login(login);
	}


	@PostMapping("/refreshtoken")
	public UserDetailsDTO refresh(@RequestBody RefreshTokenDTO tokenDTO, HttpServletRequest request, HttpServletResponse response) {
		if(!(tokenDTO.getToken()==null || tokenDTO.getToken().isEmpty()))
		{
			return authservice.refreshToken(tokenDTO.getToken());
		}
		else {
			return new UserDetailsDTO("token not present in input", "", false);
		}
	}


	@GetMapping("/config")
	public List<ConfigDTO> fetchConfig() {
		log.debug("fetch config data service start");
		return authservice.fetchConfigData();
	}
	
	

}
