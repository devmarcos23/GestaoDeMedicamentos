package com.example.demo.repository;

/*@Author https://github.com/devmarcos23*/
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Lote;

@Repository
public interface LoteRepository extends CrudRepository<Lote, Integer>{
	
	@Query("SELECT l.idLote, l.dataFabricacao, l.dataValidade, l.quantidadeMedicamento FROM Lote l")
	List<Object[]> lotesParaVerificarVencimento();
	
	@Query("SELECT l.idLote, m.nomeMedicamento, f.nomeFabricante, l.quantidadeMedicamento, l.dataEntrega, l.dataFabricacao, l.dataValidade, l.idLoteUnique "
			+ "FROM Lote l "
			+ "JOIN l.idMedicamento m "
			+ "JOIN l.idFabricante f")
	List<Object[]> getAllLotesWithJoinMedicamentoAndFabricante();
	
	
	@Query("SELECT l.idLote, m.nomeMedicamento, f.nomeFabricante, l.quantidadeMedicamento, l.dataEntrega, l.dataFabricacao, l.dataValidade, l.idLoteUnique "
			+ "FROM Lote l "
			+ "JOIN l.idMedicamento m "
			+ "JOIN l.idFabricante f "
			+ "WHERE l.idLote = :idLote ")
	List<Object[]> getAllLotesWithJoinMedicamentoAndFabricanteById(@Param("idLote") Integer idLote);
	
	//faz a contagem de lotes
	@Query("SELECT COUNT(l.idLote) FROM Lote l")
	Integer contagemDeLotes();
	
	
	//buscar todos os ids lote com id medicamento especifico
	@Query("SELECT l.idLoteUnique, l.idLote from Lote l WHERE l.idMedicamento.idMedicamento = :id")
	List<Object[]> findAllIdsLotes(@Param("id") String id);
	
	//retorn atributos da tabela lote e medicamento atravez de um id
	@Query("SELECT l.idLote, f.nomeFabricante, l.quantidadeMedicamento, l.dataEntrega, l.dataFabricacao, l.dataValidade  " +
		       "FROM Lote l " +
		       "JOIN l.idFabricante f " +
		       "WHERE l.idMedicamento.idMedicamento = :idMedicamento")
	List<Object[]> findAllLoteWithJoinFabricanteByIdMedicamento(@Param("idMedicamento") String idMedicamento);

	
	//faz a contagem de lotes e soma a quantidade de medicamentos passado um id
	@Query("SELECT COUNT(l.idLote) FROM Lote l WHERE l.idMedicamento.idMedicamento = :id GROUP BY l.idMedicamento")
	Integer quantidadeDeLotesByIdMedicamento(@Param("id") String id);
	
	
	@Query("SELECT SUM(l.quantidadeMedicamento) FROM Lote l WHERE l.idMedicamento.idMedicamento = :id GROUP BY l.idMedicamento")
	Integer quantidadeDeMedicamentosByIdMedicamento(@Param("id") String id);
	
	@Query("SELECT SUM(l.quantidadeMedicamento) FROM Lote l")
	Integer totalMedicamentosDisponiveis();
	
	
	//realiza a subtraçao da quantidade de medicamento da tabela lote onde for encontrado o id passado
	@Modifying
	@Query("UPDATE Lote SET quantidadeMedicamento = quantidadeMedicamento - :quantidade WHERE idLoteUnique = :idLoteUnique")
	void atualizarQuantidadeByIdLoteUnique(@Param("quantidade") int quantidade, @Param("idLoteUnique") Integer idLoteUnique);

}
