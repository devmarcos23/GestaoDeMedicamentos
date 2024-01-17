package com.example.demo.domain;

/*@Author https://github.com/devmarcos23*/
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario implements UserDetails{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	@SequenceGenerator(name = "usuario_seq", sequenceName = "sequencias", initialValue = 1, allocationSize = 1)
	private int idUsuario;
	
	@Column( nullable = false)
	private String nomeUsuario;
	
	@Column( nullable = false, unique = true)
	private String username;
	
	@Column( nullable = false)
	private String password;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private UserRole cargo;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		switch (this.cargo) {
		
		case SuperAdministrador:
			
			return List.of(new SimpleGrantedAuthority("Super-Administrador"));
		case Administrador:
			
			return List.of(new SimpleGrantedAuthority("Administrador"));
		case Usuario:
			
			return List.of(new SimpleGrantedAuthority("Usuario"));
		default:
			return null;
			
		}
		
	}
	
	
	public Usuario() {
		
		super();
	}



	public Usuario(int idUsuario, String nomeUsuario, String username, String password, UserRole cargo) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.username = username;
		this.password = password;
		this.cargo = cargo;
	}


	
	
	@Override
	public String toString() {
		return "" + nomeUsuario + "" + username + "" + cargo + "";
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public UserRole getCargo() {
		return cargo;
	}


	public void setCargo(UserRole cargo) {
		this.cargo = cargo;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}




}
