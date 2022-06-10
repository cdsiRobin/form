package com.cdsi.form.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cdsi.form.models.domain.Usuario;

@Controller
public class FormContro {

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo","Formulaio del Usuario");
		return "form";
	}
	
	
	/*public String procesar(Model model, @RequestParam(name ="username") String username,
			@RequestParam String password, @RequestParam String email ) {*/
	@PostMapping("form")
	public String procesar(@Valid Usuario usuario, BindingResult result ,Model model ) {	

		model.addAttribute("titulo","Resultado del Usuario");
		
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()) );
			});
			model.addAttribute("error",errores);
			return "form";
		}
		
		model.addAttribute("usuario", usuario);		
		return "resultado";
	}
	
}
