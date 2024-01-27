package com.snapmatic.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.snapmatic.auth.dto.SignUpDTO;

@Repository
public class AuthDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	public int insertUser(SignUpDTO signup) {
		return jdbcTemplate.update("insert into users(username, password, enabled) values (?,?,?)", new Object[] {signup.getUsername(), encoder.encode(signup.getPassword()), 1});
	}
	
	public int insertAuthority(SignUpDTO signup) {
		return jdbcTemplate.update("insert into authorities(username, authority) values (?,?)", new Object[] {signup.getUsername(), "user"});
	}

}
