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
import com.example.demo.service.LoteService;
import com.example.demo.service.MedicamentoService;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoView {

	@Autowired
	private MedicamentoService medicamentoService;

	@Autowired
	private LoteService loteService;

	// exibir tela de listagem de medicamento
	@GetMapping("/listamedicamentos")
	public ModelAndView exibirListaMedicamento(Authentication auth) {
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("medicamentos/lista/listaMedicamentos");
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		
		mv.addObject("listaMedicamentos", medicamentoService.findAllMedicamentoWithLeftJoinLote());
		mv.addObject("contagemMedicamentosExistentes", medicamentoService.contagemDeMedicamentos());
		return mv;

	}

	// exibir tela de listagem de medicamento
	@PostMapping("/listacostumizada")
	public ModelAndView exibirListaMedicamentoByName(@RequestParam("dado") String dado, Authentication auth) {
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("medicamentos/lista/listaMedicamentos");
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		
		mv.addObject("listaMedicamentos", medicamentoService.findAllMedicamentoWithLeftJoinLoteByName(dado));
		mv.addObject("contagemMedicamentosExistentes", medicamentoService.contagemDeMedicamentos());
		return mv;

	}

	//exibir a tela de editar medicamento ja com os campos preenchidos
		@GetMapping("/editarmedicamento/{idMedicamento}")
		public ModelAndView editarMedicamento(Model model , RedirectAttributes redirectAttributes, @PathVariable Integer idMedicamento, Authentication auth) {
			
			Usuario usuario = (Usuario ) auth.getPrincipal();
			
			ModelAndView mv = new ModelAndView("medicamentos/editar/editarMedicamento");
			
			if (model.containsAttribute("mensagem") ) {
		        mv.addObject("mensagem", model.getAttribute("mensagem"));
		    } else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
		        mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
		    }
			
			mv.addObject("role_super_admin", UserRole.SuperAdministrador);
			mv.addObject("role_admin", UserRole.Administrador);
			mv.addObject("role_user", UserRole.Usuario);
			mv.addObject("nomeUsuario", usuario.getNomeUsuario());
			mv.addObject("cargoUsuario", usuario.getCargo());
			mv.addObject("contagemMedicamentosExistentes", medicamentoService.contagemDeMedicamentos());
			mv.addObject("medicamentoByid", medicamentoService.findMedicamentoById(idMedicamento));
			return mv;
		}
	
	
	// exibir tela de novo medicamento
	@GetMapping("/novomedicamento")
	public ModelAndView addMedicamento(Model model,Authentication auth , RedirectAttributes redirectAttributes) {
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("medicamentos/novo/novoMedicamento");
	
		if (model.containsAttribute("mensagem") ) {
	        mv.addObject("mensagem", model.getAttribute("mensagem"));
	    } else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
	        mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
	    }
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		
		mv.addObject("gruposMedicinais", medicamentoService.findAllGruposMedicinais());
		mv.addObject("contagemMedicamentosExistentes", medicamentoService.contagemDeMedicamentos());
		return mv;
	}
	
	

	// exibir tela detalhes medicamento
	@GetMapping("/detalhesmedicamento/{idMedicamento}")
	public ModelAndView detalhesMedicamento(RedirectAttributes redirectAttributes, Model model,@PathVariable String idMedicamento, Authentication auth) {
		
		Usuario usuario = (Usuario ) auth.getPrincipal();
		
		ModelAndView mv = new ModelAndView("medicamentos/detalhes/detalhesMedicamento");
		
		if (model.containsAttribute("mensagem") ) {
	        mv.addObject("mensagem", model.getAttribute("mensagem"));
	    } else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
	        mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
	    }
		
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());
		
		
		mv.addObject("idLotes", loteService.findAllIdsLotes(idMedicamento));
		mv.addObject("informacoesLote", loteService.findAllLoteWithJoinFabricanteByIdMedicamento(idMedicamento));
		mv.addObject("quantidadeDeMedicamentos", loteService.quantidadeDeMedicamentosByIdMedicamento(idMedicamento));
		mv.addObject("quantidadeDeLotes", loteService.quantidadeDeLotesByIdMedicamento(idMedicamento));
		
		mv.addObject("contagemMedicamento", medicamentoService.contagemDeMedicamentos());
		mv.addObject("medicamentoPorId", medicamentoService.findMedicamentoById(Integer.parseInt(idMedicamento)));

		return mv;
	}
}
