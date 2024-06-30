package ar.edu.unju.fi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
	private long codigo;
	private String nombre;
	private String curso;
	private String cantidadHoras;
	private boolean modalidad;
	@Autowired
	private Docente docente;
	@Autowired
	private Carrera carrera;
}
