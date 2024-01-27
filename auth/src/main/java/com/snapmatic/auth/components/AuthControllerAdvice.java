package com.snapmatic.auth.components;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.snapmatic.auth.dto.ResponseDTO;

@ControllerAdvice
public class AuthControllerAdvice {
	
	
	@ExceptionHandler(value=DataAccessException.class)
	public ResponseEntity<ResponseDTO> databaseException(DataAccessException dataAccess){
		dataAccess.printStackTrace();
		ResponseDTO response=new ResponseDTO(-1, dataAccess.getMessage(), false);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value=BadCredentialsException.class)
	public ResponseEntity<ResponseDTO> badCredentialException(BadCredentialsException exception){
		exception.printStackTrace();
		ResponseDTO response=new ResponseDTO(-1, "Bad Credentials: "+exception.getMessage(), false);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.UNAUTHORIZED);
	}

}
