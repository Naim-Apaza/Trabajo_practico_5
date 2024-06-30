package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {
	@NotBlank(message = "DNI no puede estar vacío")
	private String dni;

	@NotBlank(message = "Nombre no puede estar vacío")
	private String nombre;

	@NotBlank(message = "Apellido no puede estar vacío")
	private String apellido;

	@Email(message = "Email debe ser válido")
	@NotBlank(message = "Email no puede estar vacío")
	private String email;

	@Pattern(regexp = "\\d{10}", message = "Teléfono debe tener 10 dígitos")
	private String telefono;

	@NotBlank(message = "Domicilio no puede estar vacío")
	private String domicilio;

	@NotBlank(message = "LU no puede estar vacío")
	private String lu;

	@NotBlank(message = "Fecha de nacimiento no puede estar vacío")
	private String fechaNacimiento;
}