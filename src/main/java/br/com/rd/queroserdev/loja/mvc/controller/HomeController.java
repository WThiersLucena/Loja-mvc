package br.com.rd.queroserdev.loja.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.rd.queroserdev.loja.mvc.model.Pedido;
import br.com.rd.queroserdev.loja.mvc.repository.PedidoRepository;

@Controller
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/home")
	public String home(Model model) {

		List<Pedido> pedidos = pedidoRepository.findAll();

		model.addAttribute("pedido", pedidos);

		return "home";
	}

}
