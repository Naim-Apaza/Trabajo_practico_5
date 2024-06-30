package ar.edu.unju.fi.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.model.Docente;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private Docente docente;

	private final AtomicLong COUNTER = new AtomicLong(3);

	@GetMapping("/lista")
	public String getDocentesPage(Model model) {
		model.addAttribute("titulo", "Docentes");
		model.addAttribute("docentes", CollectionDocente.getDocentes());

		return "docentes";
	}

	@GetMapping("/nuevo")
	public String getNuevoDocente(Model model) {
		boolean edicion = false;
		model.addAttribute("titulo", "Nuevo docente");
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docente);

		return "docenteFormulario";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarDocente(@ModelAttribute("docente") Docente docente, Model model) {
		docente.setLegajo("DC" + COUNTER.incrementAndGet());
		model.addAttribute("titulo", "Docentes");
		ModelAndView modelAndView = new ModelAndView("docentes");
		CollectionDocente.agregarDocente(docente);
		modelAndView.addObject("docentes", CollectionDocente.getDocentes());

		return modelAndView;
	}

	@GetMapping("/editar/{legajo}")
	public String getModificarDocente(@PathVariable(value = "legajo") String legajo, Model model) {
		Docente docente = CollectionDocente.buscarDocente(legajo);
		boolean edicion = true;
		model.addAttribute("titulo", "Modificar docente");
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docente);

		return "docenteFormulario";
	}

	@PostMapping("/editar")
	public String postModificarDocente(@ModelAttribute("docente") Docente docente) {
		
		CollectionDocente.modificarDocente(docente);

		return "redirect:/docente/lista";
	}

	@GetMapping("/eliminar/{legajo}")
	public String eliminarDocente(@PathVariable(value = "legajo") String legajo) {
		CollectionDocente.eliminarDocente(legajo);

		return "redirect:/docente/lista";
	}

}
