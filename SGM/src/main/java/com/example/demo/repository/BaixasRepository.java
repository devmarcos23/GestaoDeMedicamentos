package com.example.demo.repository;


/*@Author https://github.com/devmarcos23*/
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Baixas;

@Repository
public interface BaixasRepository extends CrudRepository<Baixas, Integer>{

		@Query("SELECT SUM(b.quantidadeMedicamento) FROM Baixas b")
		Integer volumeDeBaixas();
		
		@Query("SELECT COUNT(b.idControleSaida) FROM Baixas b")
		Integer contagemBaixas();
		
		
		@Query("SELECT  b.nomeUsuario, b.nomeMedicamento, b.quantidadeMedicamento, b.dataSaida, l.idLote "
				+ "FROM Baixas b "
				+ "JOIN Lote l "
				+ "ON b.idLoteUnique.idLoteUnique = l.idLoteUnique")
		List<Object[]> findAllBaixasWithJoinLote();
		
		@Query("SELECT  b.nomeUsuario, b.nomeMedicamento, b.quantidadeMedicamento, b.dataSaida, l.idLote "
				+ "FROM Baixas b "
				+ "JOIN Lote l "
				+ "ON b.idLoteUnique.idLoteUnique = l.idLoteUnique "
				+ "WHERE l.idLote = :idLote")
		List<Object[]> findAllBaixasWithJoinLoteById(@Param("idLote") Integer idLote);
		
		
		
		@Query("SELECT b.nomeMedicamento, SUM(b.quantidadeMedicamento) "
				+ "FROM Baixas b "
				+ "GROUP BY b.nomeMedicamento "
				+ "ORDER BY SUM(b.quantidadeMedicamento) DESC "
				+ "LIMIT 1")
		String[] findMedicamentoWithMaiorBaixas();
}

