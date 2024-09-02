package com.snapmatic.auth.components;

import com.snapmatic.auth.dto.UserDetailsDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.snapmatic.auth.dto.ResponseDTO;

@ControllerAdvice
public class AuthControllerAdvice {
	
	Logger log=LoggerFactory.getLogger(AuthControllerAdvice.class);
	
	@ExceptionHandler(value=DataAccessException.class)
	public ResponseEntity<ResponseDTO> databaseException(DataAccessException dataAccess){
		dataAccess.printStackTrace();
		ResponseDTO response=new ResponseDTO(-1, dataAccess.getMessage(), false);
		log.error("Error While Accessing Database"+dataAccess);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value=BadCredentialsException.class)
	public ResponseEntity<UserDetailsDTO> badCredentialException(BadCredentialsException exception){
		exception.printStackTrace();
		UserDetailsDTO response=new UserDetailsDTO("no token", "", false);
		log.error("Error While Accessing Database"+exception);
		return new ResponseEntity<UserDetailsDTO>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<UserDetailsDTO> jwtExpiredException(ExpiredJwtException exception) {
		exception.printStackTrace();
		UserDetailsDTO response = new UserDetailsDTO("token expired", "", false);
		log.error("token is expired"+ exception);
		return new ResponseEntity<UserDetailsDTO>(response, HttpStatus.UNAUTHORIZED);
	}

}
