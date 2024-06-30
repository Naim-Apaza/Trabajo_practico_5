package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {
	@NotNull(message = "Código no puede estar vacío")
	private long codigo;

	@NotBlank(message = "Nombre no puede estar vacío")
	private String nombre;

	@NotBlank(message = "Curso no puede estar vacío")
	private String curso;

	@NotBlank(message = "Cantidad de horas no puede estar vacío")
	private String cantidadHoras;

	private boolean modalidad;

	@NotNull(message = "Docente no puede estar vacío")
	private DocenteDTO docente;

	@NotNull(message = "Carrera no puede estar vacío")
	private CarreraDTO carrera;

}