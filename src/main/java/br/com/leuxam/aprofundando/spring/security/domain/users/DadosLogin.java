package br.com.leuxam.aprofundando.spring.security.domain.users;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
		@NotBlank
		String username,
		@NotBlank
		String password) {

}
