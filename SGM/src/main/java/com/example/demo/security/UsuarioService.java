package com.example.demo.security;
/*@Author https://github.com/devmarcos23*/
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserRole;
import com.example.demo.domain.Usuario;
import com.example.demo.repository.UsuariosRepository;


@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		 Usuario usuario =  usuariosRepository.findByLogin(username);
		 if (usuario == null) {
			
	            throw new UsernameNotFoundException("Usuário não encontrado");
	     }
		 
		 
	     
	     return usuario;
		     
	}
	
	
	
	
	
	public String findByUserName(String username){
		Optional<Usuario> usuario = usuariosRepository.findByUsername(username);
		
		if (usuario.isPresent()) {
			
			return "true";
		} else {

			return "false";
		}
	}
	
   /* *Metodo opcional*
	Esse metodo substitui a necessidade do usuario de realizar uma persistencia de um primeiro usuario do sistema
	feita diretamente no banco*/
	public void criarUsuarioSuperAdminCasoNaoExista() {
		if(usuariosRepository.findByLogin("<username>") == null) {
			
			Usuario admin = new Usuario();
			admin.setNomeUsuario("<nome_usuario>");
			admin.setUsername("<username>");
			admin.setPassword(encoder.encode("<senha>"));
			admin.setCargo(UserRole.SuperAdministrador);
			usuariosRepository.save(admin);
		}
	}
	
	
	public void addUsuario(Usuario usuario, String password) {
		usuario.setPassword(encoder.encode(password));
		usuariosRepository.save(usuario);
	}
	
	
	public Iterable<Usuario> getAllUsuarios() {
		return usuariosRepository.findAll();
	}
	
	
	public Integer contagemUsuarios() {
		return usuariosRepository.contagemUsuarios();
	}
	
	
	public void deletarUsuario(Integer idUsuario) {
		usuariosRepository.deleteById(idUsuario);
	}

	
}
