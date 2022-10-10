package br.com.rd.queroserdev.loja.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {
	
	@GetMapping("/hello")
	public String hello(Model model) {
		
		model.addAttribute("nome", " Olá Mundo");
		return "hello";
	}
}
