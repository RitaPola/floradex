package com.floradex.security;






import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtGenerator {
	

	public String generateToken(Authentication authentication) {
		String username =  authentication.getName();
		Date currentDate = new Date();
		//@SuppressWarnings("deprecation")
		Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
		return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET).compact();
	}
	
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT scaduto o non corretto");
		}
	}

}
