package com.example.demo.views;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.UserRole;
import com.example.demo.domain.Usuario;
import com.example.demo.service.BaixasService;

@Controller
@RequestMapping("/baixas")
public class BaixasView {

	@Autowired
	private BaixasService baixasService;
	
	@GetMapping
	public ModelAndView exibirListaBaixas(Authentication auth) {
		
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("baixas/baixas");
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		mv.addObject("contagemDeDispensa", baixasService.contagemBaixas());
		mv.addObject("totalControleSaida", baixasService.findAllBaixasWithJoinLote());
		return mv;
		
	}
	
	
	@PostMapping("/listacostumizada")
	public ModelAndView exibirListaBaixasById(Authentication auth, @RequestParam("idLote") Integer idLote) {
		
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("baixas/baixas");
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		mv.addObject("contagemDeDispensa", baixasService.contagemBaixas());
		mv.addObject("totalControleSaida", baixasService.findAllBaixasWithJoinLoteById(idLote));
		return mv;
		
	}
}
