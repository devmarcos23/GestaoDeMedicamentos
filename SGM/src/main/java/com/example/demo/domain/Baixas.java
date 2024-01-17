package com.example.demo.domain;
/*@Author https://github.com/devmarcos23*/

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name = "baixas")
public class Baixas {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "controle_saida_seq")
	@SequenceGenerator(name = "controle_saida_seq", sequenceName = "sequencias", initialValue = 1, allocationSize = 1)
	private int idControleSaida;
	
	@Column(nullable = false)
	private String nomeUsuario;
	
	@Column(nullable = false)
	private String nomeMedicamento;
	
	@Column(nullable = false)
	private int quantidadeMedicamento;
	
	@Column(length = 20,nullable = false)
	private String dataSaida;
	
	@ManyToOne
    @JoinColumn(name = "idLoteUnique", referencedColumnName = "idLoteUnique", nullable = false) // Chave estrangeira para idLote
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Lote idLoteUnique;

	public Baixas() {
		super();
	}

	public Baixas(int idControleSaida, String nomeUsuario,String nomeMedicamento, int quantidadeMedicamento, String dataSaida,
			Lote idLoteUnique) {
		super();
		this.idControleSaida = idControleSaida;
		this.nomeUsuario = nomeUsuario;
		this.nomeMedicamento = nomeMedicamento;
		this.quantidadeMedicamento = quantidadeMedicamento;
		this.dataSaida = dataSaida;
		this.idLoteUnique = idLoteUnique;
	}

	public int getIdControleSaida() {
		return idControleSaida;
	}

	public void setIdControleSaida(int idControleSaida) {
		this.idControleSaida = idControleSaida;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}

	public int getQuantidadeMedicamento() {
		return quantidadeMedicamento;
	}

	public void setQuantidadeMedicamento(int quantidadeMedicamento) {
		this.quantidadeMedicamento = quantidadeMedicamento;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Lote getIdLoteUnique() {
		return idLoteUnique;
	}

	public void setIdLoteUnique(Lote idLoteUnique) {
		this.idLoteUnique = idLoteUnique;
	}

	
	
	
	
	
	
}
