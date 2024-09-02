package com.snapmatic.auth.service;

import java.util.HashMap;
import java.util.List;

import com.snapmatic.auth.controller.AuthController;
import com.snapmatic.auth.dao.ConfigDao;
import com.snapmatic.auth.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.snapmatic.auth.components.JwtUtil;
import com.snapmatic.auth.dao.AuthDao;

@Service
public class AuthService {
	
	@Autowired
	AuthDao authdao;

	@Autowired
	ConfigDao configDao;
	
	@Autowired
	AuthenticationManager authmanager;
	
	@Autowired
	JwtUtil jwt;

	Logger log=LoggerFactory.getLogger(AuthService.class);


	public ResponseDTO signup(SignUpDTO signup) {
		authdao.insertUser(signup);
		authdao.insertAuthority(signup);
		return new ResponseDTO(1, "Successfully Signed Up", true);
	}
	
	public UserDetailsDTO login(LoginDTO login) {
		Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(login.getUsername(), login.getPassword());
		Authentication auth=authmanager.authenticate(authenticationRequest);
		if(auth.isAuthenticated()) {
			return new UserDetailsDTO(createJwtToken(auth), login.getUsername(), true);
		} else {
			return new UserDetailsDTO("wrong uid/password", "Authentication failed", false);
		}
	}
	
	public String createJwtToken(Authentication auth) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		return jwt.createToken(map ,auth.getName());
	} 
	
	public UserDetailsDTO refreshToken(String token) {
		log.debug("token"+token);
		UserDetailsDTO userdetails;
		boolean isTokenExpired = jwt.isTokenExpired(token);
		String username = jwt.extractUsername(token);
		if(isTokenExpired) {
			userdetails=new UserDetailsDTO("token expired", "", false);
		} else if(username == null || username.isEmpty()) {
			userdetails=new UserDetailsDTO("token expired", "", false);
		} else {
			HashMap<String, Object> map=new HashMap<String, Object>();
			userdetails=new UserDetailsDTO(jwt.createToken(map, username), username, true);
		}
		return userdetails;
	}

	public List<ConfigDTO> fetchConfigData(){
		return configDao.fetchAllProperties();
	}

}
