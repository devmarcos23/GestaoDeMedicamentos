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
import com.example.demo.service.LoteService;
import com.example.demo.service.MedicamentoService;

@Controller
@RequestMapping("/lotes")
public class LoteView {

	@Autowired
	private FabricanteService fabricanteService;

	@Autowired
	private MedicamentoService medicamentoService;

	@Autowired
	private LoteService loteService;

	@GetMapping("/listalotes")
	public ModelAndView exibirListaLotes(RedirectAttributes redirectAttributes, Model model,Authentication auth) {
		ModelAndView mv = new ModelAndView("lote/lista/listaLotes");

		Usuario usuario = (Usuario) auth.getPrincipal();
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

		mv.addObject("contagemLotes", loteService.contagemLotes());
		mv.addObject("listagemLotes", loteService.getAllLotesWithJoinMedicamentoAndFabricante());
		return mv;
	}

	@PostMapping("/listacostumizada")
	public ModelAndView exibirListaLotesById(Authentication auth, @RequestParam("idLote") Integer idLote) {
		ModelAndView mv = new ModelAndView("lote/lista/listaLotes");

		Usuario usuario = (Usuario) auth.getPrincipal();

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());

		mv.addObject("contagemLotes", loteService.contagemLotes());
		mv.addObject("listagemLotes", loteService.getAllLotesWithJoinMedicamentoAndFabricanteById(idLote));
		return mv;
	}

	@GetMapping("/editarlote/{idLoteUnique}")
	public ModelAndView exibirEditarLote(RedirectAttributes redirectAttributes, Model model,@PathVariable("idLoteUnique") Integer idLoteUnique, Authentication auth) {
		ModelAndView mv = new ModelAndView("lote/editar/editarLote");
		if (model.containsAttribute("mensagem")) {
			mv.addObject("mensagem", model.getAttribute("mensagem"));
		} else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
			mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
		}
		Usuario usuario = (Usuario) auth.getPrincipal();

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());

		mv.addObject("contagemLotes", loteService.contagemLotes());
		mv.addObject("nomesMedicamentosAndId", medicamentoService.findNomesMedicamentoAndId());
		mv.addObject("nomesFabricantesAndId", fabricanteService.nomesFabricantesAndId());
		mv.addObject("findLoteByid", loteService.findLoteById(idLoteUnique));
		return mv;
	}

	@GetMapping("/novolote")
	public ModelAndView addLote(RedirectAttributes redirectAttributes, Model model, Authentication auth) {
		ModelAndView mv = new ModelAndView("lote/novo/novoLote");
		if (model.containsAttribute("mensagem")) {
			mv.addObject("mensagem", model.getAttribute("mensagem"));
		} else if (redirectAttributes.getFlashAttributes().containsKey("mensagem")) {
			mv.addObject("mensagem", redirectAttributes.getFlashAttributes().get("mensagem"));
		}

		Usuario usuario = (Usuario) auth.getPrincipal();

		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());

		mv.addObject("contagemLotes", loteService.contagemLotes());
		mv.addObject("nomesMedicamentosAndId", medicamentoService.findNomesMedicamentoAndId());
		mv.addObject("nomesFabricantesAndId", fabricanteService.nomesFabricantesAndId());
		return mv;
	}

	@GetMapping("/lotesvencimentoproximo")
	public ModelAndView exibirListaLotesVencidos(Authentication auth) {

		ModelAndView mv = new ModelAndView("lote/vencimentoProximo/loteProximoVencer");

		Usuario usuario = (Usuario) auth.getPrincipal();
		mv.addObject("role_super_admin", UserRole.SuperAdministrador);
		mv.addObject("role_admin", UserRole.Administrador);
		mv.addObject("role_user", UserRole.Usuario);
		mv.addObject("nomeUsuario", usuario.getNomeUsuario());
		mv.addObject("cargoUsuario", usuario.getCargo());

		mv.addObject("lotesProximoDeVencer", loteService.verificarLotesVencidos());
		return mv;
	}
}
