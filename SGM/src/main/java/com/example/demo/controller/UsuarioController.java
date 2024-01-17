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

import com.example.demo.domain.Usuario;
import com.example.demo.security.UsuarioService;

@Controller
@RequestMapping("/usuariocontroller")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/salvarusuario")
	public String addUsuario(RedirectAttributes redirectAttributes, Usuario usuario, @RequestParam("password") String password) {
		
		usuarioService.addUsuario(usuario, password);
		
		redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");

		return "redirect:/espacoadmin";
	}
	
	@GetMapping("/deletarusuario/{idUsuario}")
	public String deletarUsuario(RedirectAttributes redirectAttributes,@PathVariable("idUsuario") Integer idUsuario) {
		usuarioService.deletarUsuario(idUsuario);
		redirectAttributes.addFlashAttribute("mensagem", "Remoção realizada com sucesso!");
		return "redirect:/espacoadmin";
	}
	
}
