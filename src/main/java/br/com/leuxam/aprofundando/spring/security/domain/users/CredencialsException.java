package br.com.leuxam.aprofundando.spring.security.domain.users;

public class CredencialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CredencialsException(String msg) {
		super(msg);
	}
	
}
