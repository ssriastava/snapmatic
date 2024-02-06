package com.snapmatic.auth.service;

import java.util.HashMap;
import java.util.List;

import com.snapmatic.auth.dao.ConfigDao;
import com.snapmatic.auth.dto.*;
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
	
	public ResponseDTO signup(SignUpDTO signup) {
		authdao.insertUser(signup);
		authdao.insertAuthority(signup);
		return new ResponseDTO(1, "Successfully Signed Up", true);
	}
	
	public ResponseDTO login(LoginDTO login) {
		Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(login.getUsername(), login.getPassword());
		Authentication auth=authmanager.authenticate(authenticationRequest);
		if(auth.isAuthenticated()) {
			return new ResponseDTO(1, createJwtToken(auth), true);
		} else {
			return new ResponseDTO(0, "Authentication failed", false);
		}
	}
	
	public String createJwtToken(Authentication auth) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		return jwt.createToken(map ,auth.getName());
	} 
	
	public ResponseDTO refreshToken(String token) {
		ResponseDTO response;
		if(jwt.isTokenExpired(token)) {
			response=new ResponseDTO(-1, "token expired", false);
		}
		else {
			HashMap<String, Object> map=new HashMap<String, Object>();
			response=new ResponseDTO(1, jwt.createToken(map, jwt.extractUsername(token)), true);
		}
		return response;
	}

	public List<ConfigDTO> fetchConfigData(){
		return configDao.fetchAllProperties();
	}

}
