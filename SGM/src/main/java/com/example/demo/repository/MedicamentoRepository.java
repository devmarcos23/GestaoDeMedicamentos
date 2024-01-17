package com.example.demo.repository;

/*@Author https://github.com/devmarcos23*/
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Medicamento;

@Repository
public interface MedicamentoRepository extends CrudRepository<Medicamento, Integer>{
	
	
	//extrai todos os nomes da tabela medicamento
	@Query("SELECT m.idMedicamento, m.nomeMedicamento FROM Medicamento m")
	List<Object[]> findNomesMedicamentoAndId();
	
	/*realiza left join da tabela medicamento com a tabela lote onde o id de ambas forem iguais, exibe todos os medicamentos
	 * e caso nao tem relacao com a tabela lotes, a soma é igual a zero*/
	@Query("SELECT m.idMedicamento, m.nomeMedicamento, m.grupoMedicinal, COALESCE(SUM(l.quantidadeMedicamento), 0) "
			+ "FROM Medicamento m "
			+ "LEFT JOIN Lote l ON l.idMedicamento.idMedicamento = m.idMedicamento "
			+ "GROUP BY m.idMedicamento, m.nomeMedicamento, m.grupoMedicinal")
	List<Object[]> findAllMedicamentoWithLeftJoinLote();

	
	
	@Query("SELECT m.idMedicamento, m.nomeMedicamento, m.grupoMedicinal, COALESCE(SUM(l.quantidadeMedicamento), 0) "
			+ "FROM Medicamento m "
			+ "LEFT JOIN Lote l ON l.idMedicamento.idMedicamento = m.idMedicamento "
			+ "WHERE m.nomeMedicamento = :dado OR m.grupoMedicinal = :dado "
			+ "GROUP BY m.idMedicamento, m.nomeMedicamento, m.grupoMedicinal")
	List<Object[]> findAllMedicamentoWithLeftJoinLoteByName(@Param("dado") String dado);
	
	
	//contagem do numero de medicamentos existentes
	@Query("SELECT COUNT(m.idMedicamento) FROM Medicamento m")
	Integer contagemDeMedicamentos();
	
	
	//contagem da quantidade de grupos medicinais existentes
	@Query("SELECT COUNT(DISTINCT m.grupoMedicinal) FROM Medicamento m")
	Integer contagemGruposMedicinaisDistintos();
	
	@Query("SELECT m.grupoMedicinal FROM Medicamento m")
	List<Object[]> findAllGruposMedicinais();
	
	@Query("SELECT m FROM Medicamento m WHERE m.nomeMedicamento = :nome")
	Optional<Medicamento> findMedicamentoByName(@Param("nome") String nome);
}
