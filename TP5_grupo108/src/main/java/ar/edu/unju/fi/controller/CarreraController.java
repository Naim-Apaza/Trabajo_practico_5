package ar.edu.unju.fi.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.model.Carrera;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private Carrera carrera;
	private final AtomicLong COUNTER = new AtomicLong(3);

	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");

		return "carreras";
	}

	@GetMapping("/nuevo")
	public String getNuevaCarrera(Model model) {
		boolean edicion = false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva carrera");

		return "carreraFormulario";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") Carrera carrera, Model model) {
		carrera.setCodigo(COUNTER.incrementAndGet());
		model.addAttribute("titulo", "Carreras");
		ModelAndView modelAndView = new ModelAndView("carreras");
		carrera.setEstado(true);
		CollectionCarrera.agregarCarrera(carrera);
		modelAndView.addObject("carreras", CollectionCarrera.getCarreras());
		return modelAndView;
	}

	@GetMapping("/modificar/{codigo}")
	public String getModificarCarrera(Model model, @PathVariable(value = "codigo") long codigo) {
		Carrera carreraEncontrada = new Carrera();
		boolean edicion = true;
		carreraEncontrada = CollectionCarrera.buscarCarrera(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontrada);
		model.addAttribute("titulo", "Modificar carrera");
		return "carreraFormulario";
	}

	@PostMapping("/modificar")
	public String postModificarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		CollectionCarrera.modificarCarrera(carrera);
		return "redirect:/carrera/listado";
	}

	@GetMapping("/eliminar/{codigo}")
	public String eliminarCarrera(@PathVariable(value = "codigo") long codigo) {
		CollectionCarrera.eliminaCarrera(codigo);
		return "redirect:/carrera/listado";
	}

}
