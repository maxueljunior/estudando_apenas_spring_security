package br.com.leuxam.aprofundando.spring.security.domain.users;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "Permissions")
@Table(name = "tb_permissions")
@AllArgsConstructor
@NoArgsConstructor
public class Permissions implements GrantedAuthority{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	
	@ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
	private List<Users> users;
	
	@Override
	public String getAuthority() {
		return descricao;
	}
	
	
}
