package com.snapmatic.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class AuthConfig {
	
	private final DataSource dataSource;

    @Autowired
    public AuthConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    public BCryptPasswordEncoder encoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsManager(DataSource datasource) {
    	return new JdbcUserDetailsManager(datasource);
    }
    
    @Bean
    public AuthenticationProvider getAuthenticationProvider(BCryptPasswordEncoder passwordencoder, UserDetailsService service) {
    	DaoAuthenticationProvider authenticationprovider=new DaoAuthenticationProvider(passwordencoder);
    	authenticationprovider.setUserDetailsService(service);
    	return authenticationprovider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
    	return new ProviderManager(authenticationProvider);
    }

}
