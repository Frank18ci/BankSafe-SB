package com.bank.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {
	@Value("${jwt.secret.key}")
	private String secretKey;
	@Value("${jwt.time.expiration}")
	private String timeExpiration;
	
	public String generateAccesToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.signWith(getSignaturekey(), SignatureAlgorithm.HS256)
				.compact();
	}
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(getSignaturekey())
			.build()
			.parseClaimsJws(token)
			.getBody();
			return true;
		} catch(Exception e) {
			log.error("Token invalido, error".concat(e.getMessage()));
			return false;
		}
	}
	
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	
	public <T> T getClaim(String token, Function<Claims, T> ClaimsFunction) {
		Claims claims = extractAllClaims(token);
		return ClaimsFunction.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignaturekey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	public Key getSignaturekey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
}
