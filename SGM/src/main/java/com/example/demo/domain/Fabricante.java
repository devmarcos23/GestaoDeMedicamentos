package com.example.demo.domain;
/*@Author https://github.com/devmarcos23*/
import jakarta.persistence.*;

@Entity
@Table(name = "Fabricante")
public class Fabricante {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fabricante_seq")
	@SequenceGenerator(name = "fabricante_seq", sequenceName = "sequencias", initialValue = 1, allocationSize = 1)
	private int idFabricante;
	
	@Column(nullable = false)
	private String nomeFabricante;
	
	@Column(length = 20, unique = true , nullable = false)
	private String cnpj;
	
	@Column(length = 20, nullable = false)
	private String contato;

	public Fabricante() {
		super();
	}

	public Fabricante(int idFabricante, String nomeFabricante, String cnpj, String contato) {
		super();
		this.idFabricante = idFabricante;
		this.nomeFabricante = nomeFabricante;
		this.cnpj = cnpj;
		this.contato = contato;
	}

	public int getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNomeFabricante() {
		return nomeFabricante;
	}

	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	
	
	
	
}
