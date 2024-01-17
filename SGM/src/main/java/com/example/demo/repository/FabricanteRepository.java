package com.example.demo.repository;
/*@Author https://github.com/devmarcos23*/
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Fabricante;

@Repository
public interface FabricanteRepository extends CrudRepository<Fabricante, Integer>{
	
	//encontrar um fabricante por um paramentro especifico
	@Query("SELECT f FROM Fabricante f WHERE f.nomeFabricante = :dado")
	Iterable<Fabricante> findFabricanteByDado(@Param("dado") String dado);
	
	//contagem de fabricantes
	@Query("SELECT COUNT(f.idFabricante) FROM Fabricante f")
	Integer contagemFabricantes();
	
	
	//extrai todos os nomes da tabela fabricante
	@Query("SELECT f.idFabricante, f.nomeFabricante FROM Fabricante f")
	List<Object[]> nomesFabricantesAndId();
	

	
	@Query("SELECT f FROM Fabricante f WHERE f.cnpj = :cnpj")
	Optional<Fabricante> findByCnpj(@Param("cnpj") String cnpj);
	
}
