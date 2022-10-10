package br.com.rd.queroserdev.loja.mvc.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rd.queroserdev.mvc.loja.model.Pedido;
import br.com.rd.queroserdev.mvc.loja.model.StatusPedido;
import br.com.rd.queroserdev.mvc.loja.model.User;
import br.com.rd.queroserdev.mvc.loja.repository.PedidoRepository;
import br.com.rd.queroserdev.mvc.loja.repository.UserRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("cadastroUser")
	public String cadastroUser() {
		return "/usuario/cadastroUser";
	}
	
	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "usuario/home";
	}
	
	@GetMapping("pedido/{status}")
	public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.findByStatusEUsuario(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
	
	@PostMapping("cadastroUser") public String cadastro(@Valid User user, BindingResult result ) throws Exception 
	{ if (result.hasErrors())
	{ return "cadastroUser";} 
	bcryptEncoder = new BCryptPasswordEncoder(); 
	user.setPassword(bcryptEncoder.encode(user.getPassword()));
	user.setEnabled(true);
	
	userRepository.save(user); return "/login"; } 
	
	
	

	}
