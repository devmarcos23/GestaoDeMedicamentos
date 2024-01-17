package com.example.demo.controller;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Baixas;

import com.example.demo.service.BaixasService;
import com.example.demo.service.LoteService;

@Controller
@RequestMapping("/baixascontroller")
public class BaixasController {
	
	@Autowired
	private LoteService loteService;
	
	@Autowired
	private BaixasService baixasService;
	
	@PostMapping("/detalhesmedicamento/baixa/{idMedicamento}")
	public String addBaixa(RedirectAttributes redirectAttributes, @RequestParam("quantidadeMedicamento") Integer quantidadeMedicamento, @RequestParam("idLoteUnique") Integer idLoteUnique, @PathVariable("idMedicamento") String idMedicamento,Baixas baixas) {
	
		baixasService.addControleSaida(baixas);
		loteService.atualizarQuantidae(quantidadeMedicamento, idLoteUnique);
		
		redirectAttributes.addFlashAttribute("mensagem", "Baixa realizada com sucesso!");
		return "redirect:/medicamentos/detalhesmedicamento/"+ idMedicamento;
	}
	
}
