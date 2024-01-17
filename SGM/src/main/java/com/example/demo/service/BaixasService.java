package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*@Author https://github.com/devmarcos23*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Baixas;
import com.example.demo.repository.BaixasRepository;

@Service
public class BaixasService {
	
	@Autowired
	private BaixasRepository baixasRepository;
	
	private List<Object[]> formatarDatas(List<Object[]> baixas){
		for(Object[] baixa : baixas) {
			String dataBaixa = (String) baixa[3];
			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dataBaixaDate = sdf.parse(dataBaixa);
				
				SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");
				String dataBaixaFormatada = sdfOutput.format(dataBaixaDate);
				
				baixa[3] = dataBaixaFormatada;
			} catch (ParseException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return baixas;
	}
	
	
	
	public List<Object[]>findAllBaixasWithJoinLote(){
		return formatarDatas(baixasRepository.findAllBaixasWithJoinLote());
	}
	
	public List<Object[]> findAllBaixasWithJoinLoteById(Integer idLote){
		return formatarDatas(baixasRepository.findAllBaixasWithJoinLoteById(idLote));
	}
	
	
	public void addControleSaida(Baixas baixas) {
		baixasRepository.save(baixas);
	}
	
	
	
	public Integer volumeDeBaixas() {
	
		return baixasRepository.volumeDeBaixas();
	}
	
	public Integer contagemBaixas() {
		
		return baixasRepository.contagemBaixas();
	}
	

	
	public String findMedicamentoWithMaiorBaixasNome() {
		
		String[] objeto = baixasRepository.findMedicamentoWithMaiorBaixas();
		if (objeto.length == 0) {
			return null;
		}
		return objeto[0].split(",")[0];
		 
	}
	
	public String findMedicamentoWithMaiorBaixasQuantidade() {
		String[] objeto = baixasRepository.findMedicamentoWithMaiorBaixas();
		if (objeto.length == 0) {
			return null;
		}
		return objeto[0].split(",")[1];
		 
	}
}
