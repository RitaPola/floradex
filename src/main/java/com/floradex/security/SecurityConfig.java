package com.floradex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; 

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthEntryPoint authEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration)
	throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		.cors().and().csrf().disable()
		.authorizeHttpRequests()
		//.requestMatchers("/api/auth/signup").permitAll()
		.requestMatchers("/**").permitAll()
		
		// !!! per controllare security
		//.requestMatchers("/admin/**").hasRole("ADMIN")
		//.requestMatchers("/api/**").hasRole("USER", "ADMIN")
		
		.anyRequest().authenticated().and()
		.exceptionHandling()
		.authenticationEntryPoint(authEntryPoint).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilterBefore(jwtRequestFilter , UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	
}
