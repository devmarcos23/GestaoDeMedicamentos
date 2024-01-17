package com.example.demo.controller;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Fabricante;
import com.example.demo.service.FabricanteService;

@Controller
@RequestMapping("/fabricantescontroller")
public class FabricanteController {

	@Autowired
	private FabricanteService fabricanteService;
	
	 
	//adicionar um novo fabricante
	@PostMapping("/novofabricante")
	public String addFabricante(RedirectAttributes redirectAttributes,Fabricante fabricante){
		fabricanteService.addFabricante(fabricante);
		redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return "redirect:/fabricantes/novofabricante";
	}
	
	//editar um fabricante
	@PostMapping("/editarfabricante/{idFabricante}")
    public String editarFabricante(RedirectAttributes redirectAttributes,@PathVariable Integer idFabricante,@ModelAttribute Fabricante fabricante ) {
    	
        fabricanteService.updateFabricante(fabricante, idFabricante);
        redirectAttributes.addFlashAttribute("mensagem", "Edição realizada com sucesso!");
        return "redirect:/fabricantes/editarfabricante/" + idFabricante;
    }
	
	
	/*/deletar um fabricante
	@GetMapping("/deletarfabricante/{idFabricante}")
	public String deletarFabricante(@PathVariable Integer idFabricante) {
		fabricanteService.deletarFabricante(idFabricante);
		return "redirect:/fabricantes/listafabricantes";
	}*/
	
}
