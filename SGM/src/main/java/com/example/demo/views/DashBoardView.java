package com.example.demo.views;

/*@Author https://github.com/devmarcos23*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.UserRole;
import com.example.demo.domain.Usuario;
import com.example.demo.service.BaixasService;
import com.example.demo.service.FabricanteService;
import com.example.demo.service.LoteService;

@Controller
@RequestMapping("/dashboard")
public class DashBoardView {
	
	@Autowired
	private FabricanteService fabricanteService;
	

	@Autowired
	private BaixasService baixasService;
	
	@Autowired
	private LoteService loteService;
	
	
	
	
	
	
	@GetMapping
	public ModelAndView exibirDashboard(Authentication auth) {
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("dashboard/dashboard");
		
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		mv.addObject("contagemLotesVencidos", loteService.verificarLotesVencidos().size());
		mv.addObject("totalfabricantes", fabricanteService.contagemFabricantes());
		mv.addObject("totalMedicamentosDisponiveis", loteService.totalMedicamentosDisponiveis());
		mv.addObject("totalLotes", loteService.contagemLotes());
		mv.addObject("totalMedicamentosDispensados", baixasService.volumeDeBaixas());
		mv.addObject("medicamentoMaisDispensadoNome", baixasService.findMedicamentoWithMaiorBaixasNome());
		mv.addObject("medicamentoMaisDispensadoQuantidade", baixasService.findMedicamentoWithMaiorBaixasQuantidade());
		return mv;
	}
}

