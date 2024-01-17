package com.example.demo.service;

/*@Author https://github.com/devmarcos23*/
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Medicamento;
import com.example.demo.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	
	
	
	public List<Object[]> findNomesMedicamentoAndId(){
		return medicamentoRepository.findNomesMedicamentoAndId(); 
	}
	
	public List<Object[]> findAllMedicamentoWithLeftJoinLote(){
		
		return medicamentoRepository.findAllMedicamentoWithLeftJoinLote();
	}
	
	public List<Object[]> findAllMedicamentoWithLeftJoinLoteByName(String dado){
		
		return medicamentoRepository.findAllMedicamentoWithLeftJoinLoteByName(dado);
	}
	
	public Integer contagemDeMedicamentos() {
	
		return medicamentoRepository.contagemDeMedicamentos();
	}
	
	public Optional<Medicamento> findMedicamentoById(Integer id){
		
		return medicamentoRepository.findById(id);
	}
	
	public Optional<Medicamento> findMedicamentoByName(String dado){
		return medicamentoRepository.findMedicamentoByName(dado);
	}
	
	public Integer contagemGruposMedicinaisDistintos() {
	
		return medicamentoRepository.contagemGruposMedicinaisDistintos();
	}
	
	public List<Object[]> findAllGruposMedicinais(){
		return medicamentoRepository.findAllGruposMedicinais();
	}
	
	public void addMedicamento(Medicamento medicamento) {
		medicamentoRepository.save(medicamento);
	}
	
	public void atualizarMedicamento(Integer idMedicamento, Medicamento medicamento) {
		medicamento.setIdMedicamento(idMedicamento);
		medicamentoRepository.save(medicamento);
	}
	
	public void deletarMedicamento(Integer idMedicamento) {
		medicamentoRepository.deleteById(idMedicamento);
	}
	
	public String verificarMedicamentoExistente(String nome) {
		Optional<Medicamento> medicamento = medicamentoRepository.findMedicamentoByName(nome);
		
		if (medicamento.isPresent()) {
			return "true";
		} else {
			return "false";
		}
	}
}
