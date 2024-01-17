package com.example.demo.domain;
/*@Author https://github.com/devmarcos23*/
import jakarta.persistence.*;

@Entity
@Table(name = "Medicamento")
public class Medicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicamento_seq")
	@SequenceGenerator(name = "medicamento_seq", sequenceName = "sequencias", initialValue = 1, allocationSize = 1)
	private int idMedicamento;
	
	@Column( unique = true, nullable = false)
	private String nomeMedicamento;
	
	@Column(nullable = false)
	private int quantidadeMinima;
	
	@Column( nullable = false)
	private String grupoMedicinal;
	

	public Medicamento() {
		super();
	}


	public Medicamento(int idMedicamento, String nomeMedicamento, int quantidadeMinima, String grupoMedicinal) {
		super();
		this.idMedicamento = idMedicamento;
		this.nomeMedicamento = nomeMedicamento;
		this.quantidadeMinima = quantidadeMinima;
		this.grupoMedicinal = grupoMedicinal;
	}


	public int getIdMedicamento() {
		return idMedicamento;
	}


	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}


	public String getNomeMedicamento() {
		return nomeMedicamento;
	}


	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}


	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}


	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}


	public String getGrupoMedicinal() {
		return grupoMedicinal;
	}


	public void setGrupoMedicinal(String grupoMedicinal) {
		this.grupoMedicinal = grupoMedicinal;
	}
	
	
	
	
	
	
}
