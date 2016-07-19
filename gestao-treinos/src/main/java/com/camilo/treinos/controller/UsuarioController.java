package com.camilo.treinos.controller;

import com.camilo.treinos.model.Usuario;
import com.camilo.treinos.repository.filter.UsuarioFilter;
import com.camilo.treinos.service.CadastroUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UsuarioController {

	
	private static final String CADASTRO_USUARIO_VIEW = "cadastro-usuario";

	@Autowired
	CadastroUsuarioService usuarioService;
	
	@RequestMapping("login")
	public ModelAndView loginForm(@ModelAttribute("filtro") UsuarioFilter filtro ) {		
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "logar", method = RequestMethod.GET)
	public ModelAndView login(UsuarioFilter filtro, RedirectAttributes attr) {
		
		//MV com destino de sucesso
		ModelAndView mv = new ModelAndView("home-usuario");

		//tenta carregar o usuario pelo login
		Usuario usuario = usuarioService.verificaLogin(filtro);
		
		//o login não foi encontrado
		if (usuario == null) {

			//redireciona para a mesma página atualizando com a msg de erro
			mv.setViewName("redirect:/login");
			attr.addFlashAttribute("mensagem", "Login inválido!");
			
			//erro
			return mv;
		}
		
		//sucesso
		return mv;
	}

	@RequestMapping("home")
	public String home() {
		return "home-usuario";
	}

	@RequestMapping
	public String index() {
		return "index";
	}
	
	@RequestMapping("cadastro")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIO_VIEW);
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@RequestMapping(value="cadastrar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIO_VIEW);
		mv.addObject("status", "ERRO");

		if (errors.hasErrors()) {
			return mv;
		}

		try {
			//salva usuário
			usuarioService.salvar(usuario);

			//seta redirect attributes
			attributes.addFlashAttribute("mensagem", "Sucesso!");
			attributes.addFlashAttribute("status", "OK");
			attributes.addFlashAttribute("usuarioLogado", usuario.getEmail());

			//redireciona
			mv.setViewName("redirect:/cadastro");
			
			return mv;
		}
		catch (IllegalArgumentException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return mv;
		}		
		catch (UnsupportedOperationException e) {
			errors.rejectValue("email", null, e.getMessage());
			return mv;
		}		
	}
}
