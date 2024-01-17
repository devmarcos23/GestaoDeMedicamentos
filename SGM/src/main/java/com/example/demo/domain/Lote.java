package com.example.demo.domain;

/*@Author https://github.com/devmarcos23*/
import jakarta.persistence.*;

@Entity
@Table(name = "Lote")
public class Lote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lote_seq")
	@SequenceGenerator(name = "lote_seq", sequenceName = "sequencias", initialValue = 1, allocationSize = 1)
	private int idLoteUnique;
	
	@Column(nullable = false, unique = true)
	private int idLote;
	
	@Column(length = 20, nullable = false)
	private String dataEntrega;
	
	@Column(nullable = false)
	private int quantidadeMedicamento;
	
	@Column(length = 20, nullable = false)
	private String dataFabricacao;
	
	@Column(length = 20, nullable = false)
	private String dataValidade;
	
	@ManyToOne
    @JoinColumn(name = "idMedicamento", referencedColumnName = "idMedicamento", nullable = false) // Chave estrangeira para idMedicamento
	
	private Medicamento idMedicamento;
	
	
	@ManyToOne
    @JoinColumn(name = "idFabricante", referencedColumnName = "idFabricante", nullable = false) // Chave estrangeira para idfabricante
	
	private Fabricante idFabricante;

	
	
	public Lote() {
		super();
	}


	

	public Lote(int idLoteUnique, int idLote, String dataEntrega, int quantidadeMedicamento, String dataFabricacao,
			String dataValidade, Medicamento idMedicamento, Fabricante idFabricante) {
		super();
		this.idLoteUnique = idLoteUnique;
		this.idLote = idLote;
		this.dataEntrega = dataEntrega;
		this.quantidadeMedicamento = quantidadeMedicamento;
		this.dataFabricacao = dataFabricacao;
		this.dataValidade = dataValidade;
		this.idMedicamento = idMedicamento;
		this.idFabricante = idFabricante;
	}




	public int getIdLoteUnique() {
		return idLoteUnique;
	}




	public void setIdLoteUnique(int idLoteUnique) {
		this.idLoteUnique = idLoteUnique;
	}




	public int getIdLote() {
		return idLote;
	}




	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}




	public String getDataEntrega() {
		return dataEntrega;
	}




	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}




	public int getQuantidadeMedicamento() {
		return quantidadeMedicamento;
	}




	public void setQuantidadeMedicamento(int quantidadeMedicamento) {
		this.quantidadeMedicamento = quantidadeMedicamento;
	}




	public String getDataFabricacao() {
		return dataFabricacao;
	}




	public void setDataFabricacao(String dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}




	public String getDataValidade() {
		return dataValidade;
	}




	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}




	public Medicamento getIdMedicamento() {
		return idMedicamento;
	}




	public void setIdMedicamento(Medicamento idMedicamento) {
		this.idMedicamento = idMedicamento;
	}




	public Fabricante getIdFabricante() {
		return idFabricante;
	}




	public void setIdFabricante(Fabricante idFabricante) {
		this.idFabricante = idFabricante;
	}




	@Override
	public String toString() {
		return String.valueOf(idLote);
	}

	
	
}
