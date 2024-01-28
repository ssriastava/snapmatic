package com.snapmatic.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
	
	@Value("${jdbc.driverClass}")
	private String driverclass;
	
	@Value("${jdbc.url}")
	private String jdbcurl;
	
	@Value("${jdbc.user}")
	private String jdbcusername;
	
	@Value("${jdbc.password}")
	private String jdbcpassword;
	

	@Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverclass);
        dataSourceBuilder.url(jdbcurl);
        dataSourceBuilder.username(jdbcusername);
        dataSourceBuilder.password(jdbcpassword);
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        return new JdbcTemplate(datasource);
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
