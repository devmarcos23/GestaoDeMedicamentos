package com.example.demo.views;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.UserRole;
import com.example.demo.domain.Usuario;
import com.example.demo.service.FabricanteService;

@Controller
@RequestMapping("/fabricantes")
public class FabricanteView {

	@Autowired
	private FabricanteService fabricanteService;

	// exibir a tela com a listagem de fabricantes
	@GetMapping("/listafabricantes")
	public ModelAndView exibirListaFabricantes(Authentication auth) {
		Usuario usuario = (Usuario) auth.getPrincipal();

		ModelAndView mv = new ModelAndView("fabricantes/lista/listaFabricantes");

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		mv.addObject("contagemFabricantes", fabricanteService.contagemFabricantes());
		mv.addObject("fabricantes", fabricanteService.findAllFabricante());
		return mv;
	}

	// listar fabricantes pelo parametro
	@PostMapping("/listacostumizada")
	public ModelAndView exibirListaFabricantesByDado(@RequestParam("nome") String nome, Authentication auth) {

		Usuario usuario = (Usuario) auth.getPrincipal();

		ModelAndView mv = new ModelAndView("fabricantes/lista/listaFabricantes");

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		mv.addObject("contagemFabricantes", fabricanteService.contagemFabricantes());
		mv.addObject("fabricantes", fabricanteService.findFabricanteByDado(nome));
		return mv;
	}

	// exibir a tela de editar fabricante ja com os campos preenchidos
	@GetMapping("/editarfabricante/{idFabricante}")
	public ModelAndView editarFabricante(RedirectAttributes redirectAttributes, Model model,
			@PathVariable Integer idFabricante, Authentication auth) {

		Usuario usuario = (Usuario) auth.getPrincipal();

		ModelAndView mv = new ModelAndView("fabricantes/editar/editarFabricante");

		if (model.containsAttribute("mensagem")) {
			mv.addObject("mensagem", model.getAttribute("mensagem"));
		} else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
			mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
		}

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		mv.addObject("contagemFabricantes", fabricanteService.contagemFabricantes());
		mv.addObject("fabricanteByid", fabricanteService.findFabricanteById(idFabricante));
		return mv;
	}

	// exibir a tela de novo fabricante
	@GetMapping("/novofabricante")
	public ModelAndView addFabricante(RedirectAttributes redirectAttributes, Model model, Authentication auth) {
		Usuario usuario = (Usuario) auth.getPrincipal();

		ModelAndView mv = new ModelAndView("fabricantes/novo/novoFabricante");

		if (model.containsAttribute("mensagem")) {
			mv.addObject("mensagem", model.getAttribute("mensagem"));
		} else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
			mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
		}

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		mv.addObject("contagemFabricantes", fabricanteService.contagemFabricantes());
		return mv;
	}

}
