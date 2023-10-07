package br.com.leuxam.aprofundando.spring.security.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.leuxam.aprofundando.spring.security.domain.users.Users;

@Service
public class TokenService {
	
	@Value("${jwt.secret.key}")
	private String secretKey;
	
	public String createToken(Users users) {
		try {
			
			Algorithm algorithm = Algorithm.HMAC512(secretKey);
			return JWT.create()
					.withIssuer("Studing security")
					.withSubject(users.getUsername())
					.withClaim("roles", users.getRoles())
					.withExpiresAt(dataDeExpiracao())
					.sign(algorithm);
			
		}catch(JWTCreationException exception) {
			throw new JWTCreationException("Erro ao criar o token!!", exception);
		}
	}
	
	public String getSubject(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(secretKey);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("Studing security")
		        .build();
		        
		    return verifier.verify(token).getSubject();
		} catch (JWTVerificationException exception){
			throw new JWTVerificationException("Token está invalido ou incorreto!!");
		}
	}
	
	private Instant dataDeExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
	
	
	
	
	
	
}
