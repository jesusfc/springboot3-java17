package com.jesusfc.springboot3java17.controller.mvc;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@AllArgsConstructor
@RequestMapping("/mvc")
public class IndexController {

	private final MessageSource messageSource;

	@GetMapping({"/index", "/", "", "/home"})
	public String index(Model model, Locale locale) {

		String textoIndex = messageSource.getMessage("texto.indexcontroller.index.titulo", null, locale);
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model, Locale locale) {
		
		UserEntity usuario = new UserEntity();
		usuario.setName("Andrés");
		usuario.setFamilyName("Guzmán");
		usuario.setEmail("andres@correo.com");

		String textoPerfil = messageSource.getMessage("texto.indexcontroller.perfil.titulo", null, locale);

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getName()));
		
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model, Locale locale) {
		String textoListar = messageSource.getMessage("texto.indexcontroller.index.titulo", null, locale);
		model.addAttribute("titulo", textoListar);
		return "listar";
	}
}
