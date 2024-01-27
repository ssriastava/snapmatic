package com.snapmatic.auth.components;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {
	
	public String createToken(Map<String,Object> map,String user) {
		return Jwts.builder().claims(map).subject(user)
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+60*15*1000))
		.signWith(getSigningKey("QWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGV"), SignatureAlgorithm.HS512).compact();
	}
	
	private Key getSigningKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	private SecretKey getPrivateSigningKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
	
	public Boolean isTokenExpired(String token) {
		
        return extractExpiration(token).before(new Date());
    }
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getPrivateSigningKey("QWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGVQWESGV"))
        		.build().parseClaimsJws(token).getPayload();
    }

}
