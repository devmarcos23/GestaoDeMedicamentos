package com.example.demo.controller;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.UsuarioService;
import com.example.demo.service.FabricanteService;
import com.example.demo.service.LoteService;
import com.example.demo.service.MedicamentoService;

@RestController
@RequestMapping("/requisicoesrest")
public class RequisicoesRestController {

	@Autowired
	private LoteService loteService;
	
	@Autowired
	private FabricanteService fabricanteService;
	
	@Autowired
	private MedicamentoService medicamentoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/verificarloteexistente/{idLote}")
	public String verificarLoteExistenteByIdLoteUnique(@PathVariable("idLote") Integer idLote){
		return loteService.existsLoteById(idLote);
	}
	
	@GetMapping("/verificarqtdloteexistente")
	public String verificarQuantidadeMedicamentoEmLote(@RequestParam("idLote") Integer idLote,@RequestParam("medicamentosRetirada") Integer medicamentosRetirada){
	
		return loteService.verificarQuantidadeMedicamentoEmLote(idLote, medicamentosRetirada);
	}
	
	
	@GetMapping("/verificarfabricanteexistente")
	public String verificarFabricanteExistente(@RequestParam("cnpj") String cnpj){
		
		return fabricanteService.verificarFabricanteExistente(cnpj);
	}
	
	@GetMapping("/verificarmedicamentoexistente/{nome}")
	public String verificarMedicamentoExistente(@PathVariable("nome") String nome){
		
		return medicamentoService.verificarMedicamentoExistente(nome);
	}
	
	@GetMapping("/verificarusuarioexistente/{username}")
	public String verificarUsuarioExistente(@PathVariable("username") String username){
		return usuarioService.findByUserName(username);
	}
}
