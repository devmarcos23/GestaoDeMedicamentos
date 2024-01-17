package com.example.demo.controller;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Lote;
import com.example.demo.service.LoteService;

@Controller
@RequestMapping("/lotescontroller")
public class LoteController {
	
	@Autowired
	private LoteService loteService;
	
	
	@PostMapping("/novolote")
	public String addLote(RedirectAttributes redirectAttributes, Lote lote){
		
		loteService.addLote(lote);
		redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return "redirect:/lotes/novolote";
	}
	
	
	@PostMapping("/editarlote")
	public String editarLote(RedirectAttributes redirectAttributes,@RequestParam("idLoteUnique") Integer idLoteUnique, Lote lote) {
		loteService.editarLote(idLoteUnique, lote);
		redirectAttributes.addFlashAttribute("mensagem", "Edição realizada com sucesso!");
		return "redirect:/lotes/listalotes";
	}
	
	@GetMapping("/deletarlote/{idLoteUnique}")
	public String deletarLote(RedirectAttributes redirectAttributes,@PathVariable("idLoteUnique") Integer idLoteUnique) {
		loteService.deletarLote(idLoteUnique);
		redirectAttributes.addFlashAttribute("mensagem", "Remoção realizada com sucesso!");
		return "redirect:/lotes/listalotes";
	}
	
	
	@GetMapping("/contagemlotesvencidos")
	public Integer verificarLotesVencidos() {
		return loteService.verificarLotesVencidos().size();
	}

	
	
	
	
}
