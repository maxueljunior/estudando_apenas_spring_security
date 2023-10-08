package br.com.leuxam.aprofundando.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/teste")
@RestController
public class TesteController {
	
	@GetMapping
	public ResponseEntity teste() {
		return ResponseEntity.ok().body("Server running...");
	}
}
