package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/*@Author https://github.com/devmarcos23*/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Lote;
import com.example.demo.repository.LoteRepository;

import jakarta.transaction.Transactional;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;

	
	private List<Object[]> formatarDatas(List<Object[]> lotes, Integer id){
		//Formatar datas para padrão brasileiro
		//formatar datas da pagina de listagem de lotes
		if(id == 0) {
			for (Object[] lote : lotes) {
	
				String dataEntrega = (String) lote[4];
				String dataFabricacao = (String) lote[5];
				String dataValidade = (String) lote[6];
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date dataEntregaString = sdf.parse(dataEntrega);
					Date dataFabricacaoString = sdf.parse(dataFabricacao);
					Date dataValidadeString = sdf.parse(dataValidade);
	
					SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dataEntregaFormatada = outputFormat.format(dataEntregaString);
					String dataFabricacaoFormatada = outputFormat.format(dataFabricacaoString);
					String dataValidadeFormatada = outputFormat.format(dataValidadeString);
	
					lote[4] = dataEntregaFormatada;
					lote[5] = dataFabricacaoFormatada;
					lote[6] = dataValidadeFormatada;
				} catch (ParseException e) {
	
					e.printStackTrace();
				}
	
			}
			//formatar datas da pagina de lotes vencidos
		}else if (id == 1){
			for (Object[] lote : lotes) {
				String dataFabricacao = (String) lote[1];
				String dataValidade = (String) lote[2];
				
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date dataValidadeDate = sdf.parse(dataValidade);
					Date dataFabricacaoDate= sdf.parse(dataFabricacao);
					
					SimpleDateFormat outPut = new SimpleDateFormat("dd-MM-yyyy");
					String dataValidadeFormatada = outPut.format(dataValidadeDate);
					String dataFabricacaoFormatda = outPut.format(dataFabricacaoDate);
					
					lote[1] = dataFabricacaoFormatda;
					lote[2] = dataValidadeFormatada;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}else if (id == 2) {
			for (Object[] lote : lotes) {
				
				String dataEntrega = (String) lote[3];
				String dataFabricacao = (String) lote[4];
				String dataValidade = (String) lote[5];
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date dataEntregaString = sdf.parse(dataEntrega);
					Date dataFabricacaoString = sdf.parse(dataFabricacao);
					Date dataValidadeString = sdf.parse(dataValidade);
	
					SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dataEntregaFormatada = outputFormat.format(dataEntregaString);
					String dataFabricacaoFormatada = outputFormat.format(dataFabricacaoString);
					String dataValidadeFormatada = outputFormat.format(dataValidadeString);
	
					lote[3] = dataEntregaFormatada;
					lote[4] = dataFabricacaoFormatada;
					lote[5] = dataValidadeFormatada;
				} catch (ParseException e) {
	
					e.printStackTrace();
				}
			}
		}
		return lotes;
	}
	
	public List<Object[]> getAllLotesWithJoinMedicamentoAndFabricante() {
		return formatarDatas(loteRepository.getAllLotesWithJoinMedicamentoAndFabricante(), 0);
	}

	public List<Object[]> getAllLotesWithJoinMedicamentoAndFabricanteById(Integer idlote) {
		return formatarDatas(loteRepository.getAllLotesWithJoinMedicamentoAndFabricanteById(idlote), 0);
	}

	public void editarLote(Integer idLoteUnique, Lote lote) {
		lote.setIdLoteUnique(idLoteUnique);
		loteRepository.save(lote);
	}

	public void deletarLote(Integer idLote) {
		loteRepository.deleteById(idLote);
	}

	public void addLote(Lote lote) {

		loteRepository.save(lote);
	}

	public Optional<Lote> findLoteById(Integer idLote) {

		return loteRepository.findById(idLote);
	}

	public Integer contagemLotes() {
		return loteRepository.contagemDeLotes();
	}

	public List<Object[]> findAllIdsLotes(String id) {
		return loteRepository.findAllIdsLotes(id);
	}

	public List<Object[]> findAllLoteWithJoinFabricanteByIdMedicamento(String id) {
		return formatarDatas(loteRepository.findAllLoteWithJoinFabricanteByIdMedicamento(id), 2);
		
	}

	public Integer quantidadeDeLotesByIdMedicamento(String id) {
		return loteRepository.quantidadeDeLotesByIdMedicamento(id);
	}

	public Integer quantidadeDeMedicamentosByIdMedicamento(String id) {
		return loteRepository.quantidadeDeMedicamentosByIdMedicamento(id);
	}

	public Integer totalMedicamentosDisponiveis() {
		return loteRepository.totalMedicamentosDisponiveis();
	}

	@Transactional
	public void atualizarQuantidae(Integer quantidade, Integer idLoteUnique) {
		loteRepository.atualizarQuantidadeByIdLoteUnique(quantidade, idLoteUnique);
	}

	public List<Object[]> verificarLotesVencidos() {
		List<Object[]> lotesVencidos = new ArrayList<>();

		LocalDate dataAtual = LocalDate.now();
		LocalDate dataVencimento = LocalDate.now().plusDays(30);

		for (Object[] objeto : loteRepository.lotesParaVerificarVencimento()) {
			LocalDate dataLote = LocalDate.parse(objeto[2].toString());
		
			if ((dataLote.isAfter(dataAtual) && dataLote.isBefore(dataVencimento)) || dataAtual.isAfter(dataLote)) {

				lotesVencidos.add(objeto);
				
			}
		}
		return formatarDatas(lotesVencidos, 1);
	}

	public String existsLoteById(Integer idLoteUnique) {
		boolean lote = loteRepository.existsById(idLoteUnique);
		if (lote) {
			return "true";

		} else {

			return "false";
		}
	}

	public String verificarQuantidadeMedicamentoEmLote(Integer idLoteUnique, Integer medicamentosRetirada) {
		Optional<Lote> lote = loteRepository.findById(idLoteUnique);
		if (lote.get().getQuantidadeMedicamento() >= medicamentosRetirada) {

			return "disponivel";
		} else {

			return "indisponivel";
		}
	}

}
