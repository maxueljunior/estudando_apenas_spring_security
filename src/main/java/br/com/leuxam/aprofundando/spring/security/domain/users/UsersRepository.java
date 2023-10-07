package br.com.leuxam.aprofundando.spring.security.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	UserDetails findByUsername(String username);
}
