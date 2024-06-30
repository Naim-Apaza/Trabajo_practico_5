package ar.edu.unju.fi.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	private Materia materia;

	private final AtomicLong COUNTER = new AtomicLong(3);

	@GetMapping("/lista")
	public String getListadoPage(Model model) {
		model.addAttribute("materias", CollectionMateria.getMateriasListado());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}

	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {
		boolean edicion = false;
		model.addAttribute("titulo", "Nueva materia");
		model.addAttribute("edicion", edicion);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("materia", materia);

		return "materiaFormulario";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarMateria(Model model, @RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "curso") String curso, @RequestParam(value = "cantidadHoras") String cantidadHora,
			@RequestParam(value = "modalidad") String modalidad, @RequestParam(value = "carrera") String codigoCarrera,
			@RequestParam(value = "docente") String legajoDocente) {
		model.addAttribute("titulo", "Materias");

		Carrera carrera = CollectionCarrera.buscarCarrera(Integer.parseInt(codigoCarrera));
		Docente docente = CollectionDocente.buscarDocente(legajoDocente);

		Materia materia = new Materia(COUNTER.incrementAndGet(), nombre, curso, cantidadHora,
				Boolean.parseBoolean(modalidad), docente, carrera);

		CollectionMateria.agregarMateria(materia);
		ModelAndView modelAndView = new ModelAndView("materias");
		modelAndView.addObject("materias", CollectionMateria.getMateriasListado());
		return modelAndView;
	}

	@GetMapping("/editar/{codigo}")
	public String modificarMateriaPage(@PathVariable(value = "codigo") String codigoMateria, Model model) {
		boolean edicion = true;
		Materia materia = CollectionMateria.buscarMateria(Long.parseLong(codigoMateria));
		model.addAttribute("titulo", "Modificar materia");
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materia);

		return "materiaFormulario";
	}

	@PostMapping("/editar")
	public String postModificarMateria(@RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "curso") String curso, @RequestParam(value = "cantidadHoras") String cantidadHoras,
			@RequestParam(value = "modalidad") String modalidad, @RequestParam(value = "carrera") String codigoCarrera,
			@RequestParam(value = "docente") String legajoDocente) {

		Carrera carrera = CollectionCarrera.buscarCarrera(Long.parseLong(codigoCarrera));
		Docente docente = CollectionDocente.buscarDocente(legajoDocente);
		
		Materia materia = new Materia(Long.parseLong(codigoCarrera), nombre, curso, cantidadHoras,
				Boolean.parseBoolean(modalidad), docente, carrera);

		CollectionMateria.modificarMateria(materia);

		return "redirect:/materia/lista";
	}
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value = "codigo") String codigo) {
		CollectionMateria.eliminarMateria(Long.parseLong(codigo));
		return "redirect:/materia/lista";
	}

}
