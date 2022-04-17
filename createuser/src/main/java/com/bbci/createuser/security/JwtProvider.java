package com.bbci.createuser.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bbci.createuser.entities.User;
import com.bbci.createuser.utils.ConstantesGenerales;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiryTime}")
	private Long expiryTime;
	
	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}
	
	public String createToken(User user) {
		
		Map<String, Object> claims = new HashMap<>();
		
		claims = Jwts.claims().setSubject(user.getEmail());
		claims.put("uuid", user.getUuid());
		Date now = new Date();
		//tiempo de expiracion 1 hora
		Date expiration = new Date(now.getTime() + expiryTime);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getEmailFromToken(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			return ConstantesGenerales.BAD_TOKEN;
		}
	}

}
