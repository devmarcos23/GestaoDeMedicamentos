package com.example.demo.views;
/*@Author https://github.com/devmarcos23*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.UserRole;
import com.example.demo.domain.Usuario;
import com.example.demo.security.UsuarioService;

@Controller
@RequestMapping("/espacoadmin")

public class AdminView {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping

	public ModelAndView espacoAdmin(RedirectAttributes redirectAttributes, Model model, Authentication auth) {
		ModelAndView mv = new ModelAndView("admin/admin");

		if (model.containsAttribute("mensagem")) {
			mv.addObject("mensagem", model.getAttribute("mensagem"));
		} else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
			mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
		}
		// obter dados do usuario atual logado no sistema
		Usuario usuario = (Usuario) auth.getPrincipal();

		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("contagemUsuarios", usuarioService.contagemUsuarios());
		mv.addObject("usuarios", usuarioService.getAllUsuarios());
		return mv;
	}
}
