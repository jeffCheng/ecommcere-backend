package com.easybuy.shopping.auth.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import  com.easybuy.shopping.auth.UserPrinciple;;

@Component
public class JwtProvider {
	
	private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${easy.buy.jwtSecret}")
	private String jwtSecret; //server side
	
	@Value("${easy.buy.jwtExpriation}")
	private int jwtExpriation; //86400
	
	public String generateJwtToken(Authentication authentication) {
		UserPrinciple userPrincipal = (UserPrinciple)authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime()+jwtExpriation*1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		//return jwtExpriation;
		
	}
	
	public boolean validateJwtToken(String authToken ) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
        	log.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
        	log.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
        	log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
        	log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
        	log.error("JWT claims string is empty -> Message: {}", e);
        }
		return false;
	}
	
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
	
}
