package com.example.demo.service;
/*@Author https://github.com/devmarcos23*/
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Fabricante;
import com.example.demo.repository.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;

	// retorna todos os fabricantes
	public Iterable<Fabricante> findAllFabricante() {
		return fabricanteRepository.findAll();
	}

	public void addFabricante(Fabricante fabricante) {
		fabricanteRepository.save(fabricante);
	}

	// retornaar fabricante pelo nome passado na barra de pesquisa
	public Iterable<Fabricante> findFabricanteByDado(String nome) {
		return fabricanteRepository.findFabricanteByDado(nome);
	}

	// retornaar fabricante pelo id
	public Optional<Fabricante> findFabricanteById(Integer id) {
		return fabricanteRepository.findById(id);
	}

	public void updateFabricante(Fabricante fabricante, Integer idFabricante) {
		fabricante.setIdFabricante(idFabricante);
		fabricanteRepository.save(fabricante);
	}

	public Integer contagemFabricantes() {
		return fabricanteRepository.contagemFabricantes();
	}

	public List<Object[]> nomesFabricantesAndId() {

		return fabricanteRepository.nomesFabricantesAndId();
	}

	public void deletarFabricante(Integer idFabricante) {
		fabricanteRepository.deleteById(idFabricante);
	}

	public String verificarFabricanteExistente(String cnpj) {
		Optional<Fabricante> fabricante = fabricanteRepository.findByCnpj(cnpj);
		if (fabricante.isPresent()) {
			return "true";
		} else {
			return "false";
		}

	}

}
