package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping("/")
	public String getIndexInicio(Model model) {
		model.addAttribute("titulo", "Facultad de Ingenieria");
		return "index";
	}
}
