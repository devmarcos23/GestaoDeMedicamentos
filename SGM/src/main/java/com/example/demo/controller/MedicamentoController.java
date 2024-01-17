package com.example.demo.controller;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Medicamento;
import com.example.demo.service.MedicamentoService;

@Controller
@RequestMapping("/medicamentoscontroller")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService medicamentoService;
	
	
	@PostMapping("/novomedicamento")
	public String addMedicamento(RedirectAttributes redirectAttributes, Medicamento medicamento) {
		
		medicamentoService.addMedicamento(medicamento);
		redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return "redirect:/medicamentos/novomedicamento";
	}
	
	@PostMapping("/editarmedicamento")
	public String editarMedicamento(RedirectAttributes redirectAttributes, Model model, @RequestParam("idMedicamento") Integer idMedicamento, @ModelAttribute Medicamento medicamento) {
		
		medicamentoService.atualizarMedicamento(idMedicamento, medicamento);
		redirectAttributes.addFlashAttribute("mensagem", "Edição realizada com sucesso!");
		return "redirect:/medicamentos/editarmedicamento/" + idMedicamento;
	}
	
	
	/*
	@GetMapping("/deletarmedicamento/{idMedicamento}")
	public String deletarMedicamento(@PathVariable Integer idMedicamento) {
		
		medicamentoService.deletarMedicamento(idMedicamento);
		return "redirect:/medicamentos/listamedicamentos";
	}*/
	
	
}
