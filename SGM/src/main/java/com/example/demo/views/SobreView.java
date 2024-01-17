package com.example.demo.views;
/*@Author https://github.com/devmarcos23*/
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.UserRole;
import com.example.demo.domain.Usuario;




@Controller
@RequestMapping("/sobrecontroller")

public class SobreView {

	@GetMapping
	
	public ModelAndView exibirSobre(Authentication auth) {
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("sobre/sobre");
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		
		return mv;
	}
}
