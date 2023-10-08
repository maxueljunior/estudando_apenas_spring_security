package br.com.leuxam.aprofundando.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leuxam.aprofundando.spring.security.domain.users.CredencialsException;
import br.com.leuxam.aprofundando.spring.security.domain.users.DadosLogin;
import br.com.leuxam.aprofundando.spring.security.domain.users.Users;
import br.com.leuxam.aprofundando.spring.security.infra.security.DadosAutenticacao;
import br.com.leuxam.aprofundando.spring.security.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<DadosAutenticacao> login(
			@RequestBody @Valid DadosLogin dados){
		var authenticate = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
		
		try {
			
			var user = authenticationManager.authenticate(authenticate);
			var token = tokenService.createToken((Users) user.getPrincipal());
			
			return ResponseEntity.ok().body(new DadosAutenticacao(token));
			
		}catch(AuthenticationException ex) {
			throw new CredencialsException("Token invalido ou expirado!!");
		}
	}
	
}
