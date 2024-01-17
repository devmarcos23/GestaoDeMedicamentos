package com.example.demo.repository;

/*@Author https://github.com/devmarcos23*/
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Usuario;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuario, Integer>{

	//contagem do numero de usuarios
	
	
	@Query("SELECT u FROM Usuario u WHERE username = :username")
	Usuario findByLogin(@Param("username") String username);
	
	@Query("SELECT u FROM Usuario u WHERE username = :username")
	Optional<Usuario> findByUsername(@Param("username") String username);
	
	
	@Query("SELECT COUNT(u.idUsuario) FROM Usuario u")
	Integer contagemUsuarios();
	
	
	
}
