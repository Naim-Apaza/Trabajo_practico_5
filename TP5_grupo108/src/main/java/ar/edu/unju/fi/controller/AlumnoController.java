package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.service.IAlumnoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	@Autowired
	private AlumnoDTO alumnoDTO;

	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping("/lista")
	public String getListaPage(Model model) {
		model.addAttribute("alumnos", alumnoService.findAll());
		model.addAttribute("titulo", "Alumnos");

		return "alumnos";
	}

	@GetMapping("/nuevo")
	public String getNuevoAlumno(Model model) {
		boolean edicion = false;
		model.addAttribute("titulo", "Nuevo alumno");
		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoDTO);

		return "alumnoFormulario";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarAlumno(Model model, @ModelAttribute(value = "alumno") AlumnoDTO alumnoDTO) {
		model.addAttribute("titulo", "Alumnos");
		boolean exito = alumnoService.save(alumnoDTO);
		String msj = exito ? "Alumno guardado" : "No se pudo guardar el alumno";

		ModelAndView modelAndView = new ModelAndView("alumnos");
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", msj);
		modelAndView.addObject("alumnos", alumnoService.findAll());

		return modelAndView;
	}

	@GetMapping("/editar/{dni}")
	public String modificarAlumnoPage(@PathVariable(value = "dni") String dni, Model model) {
		boolean edicion = true;
		AlumnoDTO alumnoDTO = alumnoService.findByDni(dni);
		model.addAttribute("titulo", "Editar alumno");
		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoDTO);

		return "alumnoFormulario";
	}

	@PostMapping("/editar")
	public String ModificarAlumno(@ModelAttribute("alumno") AlumnoDTO alumnoDTO, Model model) {
		String msj = "";
		boolean exito = false;

		try {
			alumnoService.edit(alumnoDTO);
			exito = true;
			msj = "Alumno modificado";
		} catch (Exception e) {
			msj = e.getMessage();
		}

		model.addAttribute("mensaje", msj);
		model.addAttribute("exito", exito);

		return "redirect:/alumno/lista";
	}

	@GetMapping("/eliminar/{dni}")
	public String eliminarAlumno(@PathVariable(value = "dni") String dni) {
		alumnoService.deleteByDni(dni);

		return "redirect:/alumno/lista";
	}

}
